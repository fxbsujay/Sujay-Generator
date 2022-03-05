package com.susu.generator.service.impl;

import com.susu.generator.common.ConfigUtils;
import com.susu.generator.common.DateUtils;
import com.susu.generator.dao.GeneratorDao;
import com.susu.generator.dao.TableDao;
import com.susu.generator.dao.TemplateDao;
import com.susu.generator.entity.ColumnEntity;
import com.susu.generator.entity.TableEntity;
import com.susu.generator.entity.TemplateEntity;
import com.susu.generator.exception.GeneratorException;
import com.susu.generator.service.GeneratorService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * @author fxbsujay@gmail.com
 */
@Service
@Slf4j
public class GeneratorServiceImpl implements GeneratorService {

    @Resource
    private GeneratorDao generatorDao;

    @Resource
    private TableDao tableDao;

    @Resource
    private TemplateDao templateDao;

    @Override
    public List<TableEntity> queryTableList(Map<String, Object> map) {
        return generatorDao.queryList(map);
    }

    @Override
    public TableEntity queryTable(String tableName) {
        return generatorDao.queryTable(tableName);
    }

    @Override
    public List<ColumnEntity> queryColumns(String tableName) {
        return generatorDao.queryColumns(tableName);
    }

    @Override
    public byte[] generate(Long id) {

        TableEntity table = tableDao.selectById(id);

        if (table == null) {
            throw new GeneratorException("未查询到该表！");
        }

        // 表名转换成Java类名
        String className = columnToJava(table.getTableName());
        table.setClassName(className);
        table.setClassname(StringUtils.uncapitalize(className));

        boolean hasBigDecimal = false;
        boolean hasList = false;
        //查询列信息
        List<ColumnEntity> columnList = queryColumns(table.getTableName());

        for (ColumnEntity column : columnList) {

            // 列名转换成Java属性名
            String attrName = columnToJava(column.getColumnName());
            column.setAttrName(attrName);
            column.setAttrname(StringUtils.uncapitalize(attrName));

            // 列的数据类型，转换成Java类型
            String attrType = ConfigUtils.getString(column.getDataType());
            column.setAttrType(attrType);
            if (!hasBigDecimal && attrType.equals("BigDecimal")) {
                hasBigDecimal = true;
            }
            if (!hasList && "array".equals(column.getExtra())) {
                hasList = true;
            }

            //是否主键
            if ("PRI".equalsIgnoreCase(column.getColumnKey())) {
                table.setPk(column);
            }

        }
        table.setColumns(columnList);
        // 没主键，则第一个字段为主键
        if (table.getPk() == null) {
            table.setPk(table.getColumns().get(0));
        }

        String mainPath = ConfigUtils.getString("mainPath");
        mainPath = StringUtils.isBlank(mainPath) ? "com.susu" : mainPath;

        // 封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", table.getTableName());
        map.put("tableComment", table.getTableComment());
        map.put("pk", table.getPk());
        map.put("className", table.getClassName());
        map.put("classname", table.getClassname());
        map.put("pathName", table.getClassname().toLowerCase());
        map.put("columnList", table.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("hasList", hasList);
        map.put("mainPath", mainPath);
        map.put("package", ConfigUtils.getString("package"));
        map.put("moduleName", ConfigUtils.getString("moduleName"));
        map.put("author", ConfigUtils.getString("author"));
        map.put("email", ConfigUtils.getString("email"));
        map.put("datetime", DateUtils.getTime());

        // 查询模板
        List<TemplateEntity> templateList = templateDao.selectAll();
        if (templateList.isEmpty()) {
            throw new GeneratorException("没有模板！");
        }
        Configuration configuration = configuration();

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

        return new byte[0];
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    public static void main(String[] args) {
        System.out.println(columnToJava("de_nadfe"));
    }

    /**
     * 配置 freemarker configuration
     */
    private static Configuration configuration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        configuration.setTemplateLoader(templateLoader);
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }
}
