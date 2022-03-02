package com.susu.generator.dto;

import com.susu.generator.common.base.BaseEntity;
import com.susu.generator.entity.ColumnEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableDTO extends BaseEntity {

    /**
     * 数据源id
     **/
    public Long sourceId;

    /**
     * 表名
     **/
    public String tableName;

    /**
     * 存储引擎
     **/
    public String engine;

    /**
     * 注释
     **/
    public String tableComment;

    /**
     * 类名
     **/
    public String className;


    /**
     * 类名
     **/
    public String classname;


    /**
     * 基类
     **/
    public Long baseClassId;

    /**
     * 模块名
     **/
    public String moduleName;

    /**
     * 子模块名
     **/
    public String subModuleName;

    /**
     * 包名
     **/
    public String packageName;

    /**
     * 生成路径
     **/
    public String backendPath;


    /**
     * 创建时间
     **/
    public String createTime;


    /**
     * 主键
     **/
    private ColumnEntity pk;

    /**
     * 表的列名(不包含主键)
     **/
    private List<ColumnEntity> columns;


    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
