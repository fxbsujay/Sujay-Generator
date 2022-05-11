package com.susu.generator.common.constant;

/**
 * <p> Description: 模板常量 </p>
 * @author sujay
 * @version 2:41 2022/3/20
 */
public interface TemplateConstant {

    /**
     *  ${package} 包名
     */
    String PACKAGE_NAME = "package";

    /**
     *  ${moduleName} 模块名
     */
    String MODULE_NAME = "moduleName";

    /**
     *  ${subModuleName} 子模块名
     */
    String SUB_MODULE_NAME = "subModuleName";

    /**
     *  ${projectList} 需要引入的包
     */
    String PROJECT_LIST = "projectList";

    /**
     *  ${className} 类名
     */
    String CLASS_NAME = "className";

    /**
     *  ${tableName} 表名
     */
    String TABLE_NAME = "tableName";

    /**
     *  ${tableComment} 表说明
     */
    String TABLE_COMMENT = "tableComment";

    /**
     *  ${columnList} 字段数组
     */
    String COLUMN_LIST = "columnList";

    /**
     *  ${column.comment} 字段说明
     */
    String COMMENT = "columnComment";

    /**
     *  ${column.attrType} 属性类型
     */
    String ATTR_TYPE = "attrTypeName";

    /**
     *  ${column.attrName} 属性名称
     */
    String ATTR_NAME = "attrName";
}
