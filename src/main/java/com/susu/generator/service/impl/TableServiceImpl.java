package com.susu.generator.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.susu.generator.common.*;
import com.susu.generator.common.base.BaseServiceImpl;
import com.susu.generator.common.constant.TemplateConstant;
import com.susu.generator.config.DynamicDataSourceConfig;
import com.susu.generator.dao.*;
import com.susu.generator.dto.SourceDTO;
import com.susu.generator.dto.TableDTO;
import com.susu.generator.entity.*;
import com.susu.generator.exception.GeneratorException;
import com.susu.generator.service.TableService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 26933
 */
@Service
public class TableServiceImpl  extends BaseServiceImpl<TableDao, TableEntity, TableDTO> implements TableService {

    @Resource
    private SourceDao sourceDao;

    @Resource
    private GeneratorDao generatorDao;

    @Resource
    private ColumnDao columnDao;

    @Resource
    private TemplateDao templateDao;

    @Resource
    private FieldTypeDao fieldTypeDao;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Override
    public List<TableDTO> selectTableListBySourceId(Long sourceId) {

        SourceEntity entity = sourceDao.selectById(sourceId);

        if (sourceId != null && entity != null) {
            Boolean flag = DBUtils.connTest(ConvertUtils.sourceToTarget(entity, SourceDTO.class));

            if (!flag) {
                throw new GeneratorException("数据源连接失败！");
            }

            DruidDataSource dbSource = new DruidDataSource();
            dbSource.setUrl(entity.getConnUrl());
            dbSource.setUsername(entity.getUsername());
            dbSource.setPassword(entity.getPassword());
            DynamicDataSourceConfig.dataSourcesMap.put("dbKey", dbSource);
            DynamicDataSourceConfig.setDataSource("dbKey");
        }

        List<TableEntity> list = generatorDao.queryList(new HashMap<>(1));
        DynamicDataSourceConfig.clear();
        return ConvertUtils.sourceToTarget(list,TableDTO.class);
    }

    @Override
    public void importTable(TableDTO dto) {
        Long sourceId = dto.getSourceId();
        SourceEntity entity = sourceDao.selectById(sourceId);

        Boolean flag = DBUtils.connTest(ConvertUtils.sourceToTarget(entity, SourceDTO.class));

        if (!flag) {
            throw new GeneratorException("数据源连接失败！");
        }

        DruidDataSource dbSource = new DruidDataSource();
        dbSource.setUrl(entity.getConnUrl());
        dbSource.setUsername(entity.getUsername());
        dbSource.setPassword(entity.getPassword());
        DynamicDataSourceConfig.dataSourcesMap.put("dbKey", dbSource);
        DynamicDataSourceConfig.setDataSource("dbKey");
        TableEntity tableEntity = generatorDao.queryTable(dto.getTableName());
        List<ColumnEntity> columnList = generatorDao.queryColumns(dto.getTableName());
        DynamicDataSourceConfig.clear();
        dto.setCreateTime(tableEntity.getCreateTime());
        dto.setTableComment(tableEntity.getTableComment());
        dto.setEngine(tableEntity.getEngine());

        List<FieldTypeEntity> fieldTypeEntities = fieldTypeDao.queryList(new Query());
        Map<String, Long> fieldTypeMap = fieldTypeEntities.stream()
                .collect(Collectors.toMap(FieldTypeEntity::getColumnType, FieldTypeEntity::getId));

        TransactionStatus transactionStatus = null;
        try {
            transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            super.save(dto);
            columnList.forEach( item -> {
                item.setAttrName(StringUtils.firstLetterBig(item.getColumnName(),'_'));
                item.setTableId(dto.getId());
                item.setAttrType(fieldTypeMap.get(item.getColumnType()));
            });
            columnDao.insertBatch(columnList);
            dataSourceTransactionManager.commit(transactionStatus);
        }catch (Exception e) {
            e.printStackTrace();
            assert transactionStatus != null;
            dataSourceTransactionManager.rollback(transactionStatus);
        }

    }

    @Override
    public void exportTable(Long id) {


        Query templateQuery = new Query();
        templateQuery.put("status",0);
        List<TemplateEntity> templateList = templateDao.queryList(templateQuery);
        if (templateList.isEmpty()) {
            throw new GeneratorException("没有模板！");
        }
        Configuration configuration = ConfigUtils.configuration();
        TableEntity table = baseDao.selectById(id);
        Query columnQuery = new Query();
        templateQuery.put("tableId",table.getId());
        List<ColumnEntity> columnEntities = columnDao.queryList(columnQuery);

        Map<String, Object> map = new HashMap<>();
        map.put(TemplateConstant.PACKAGE_NAME, table.getPackageName());
        map.put(TemplateConstant.MODULE_NAME, table.getModuleName());
        map.put(TemplateConstant.SUB_MODULE_NAME, table.getSubModuleName());
        map.put(TemplateConstant.TABLE_NAME,table.getTableName());
        map.put(TemplateConstant.CLASS_NAME,table.getClassName());

        try {
            for (TemplateEntity templateEntity : templateList) {

                String content = templateEntity.getContent();
                String fileName = templateEntity.getFileName();
                StringWriter stringWriter = new StringWriter();
                Template template = new Template(fileName, content, configuration);
                template.process(map, stringWriter);
                System.out.println(stringWriter.toString());
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

    }
    
    @Override
    public int delete(Long[] ids) {
        Query query = new Query();
        query.put("tableIds",ids);
        columnDao.delete(query);
        return super.delete(ids);
    }
}
