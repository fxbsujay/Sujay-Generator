package com.susu.generator.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.susu.generator.common.ConvertUtils;
import com.susu.generator.common.DBUtils;
import com.susu.generator.common.base.BaseServiceImpl;
import com.susu.generator.config.DynamicDataSourceConfig;
import com.susu.generator.dao.ColumnDao;
import com.susu.generator.dao.GeneratorDao;
import com.susu.generator.dao.SourceDao;
import com.susu.generator.dao.TableDao;
import com.susu.generator.dto.SourceDTO;
import com.susu.generator.dto.TableDTO;
import com.susu.generator.entity.ColumnEntity;
import com.susu.generator.entity.SourceEntity;
import com.susu.generator.entity.TableEntity;
import com.susu.generator.exception.GeneratorException;
import com.susu.generator.service.TableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

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
        super.save(dto);

        columnList.forEach( item -> {
            item.setTableId(dto.getId());
            columnDao.insert(item);
        });

    }
}
