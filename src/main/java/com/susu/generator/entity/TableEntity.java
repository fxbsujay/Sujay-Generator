package com.susu.generator.entity;

import com.susu.generator.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableEntity extends BaseEntity {

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

}
