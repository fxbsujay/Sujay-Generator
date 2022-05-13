DROP TABLE IF EXISTS g_column;
CREATE TABLE g_column(
    id BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '' ,
    table_id BIGINT(20)    COMMENT 'table_id' ,
    column_name VARCHAR(255)    COMMENT '字段名' ,
    column_type VARCHAR(255)    COMMENT '数据类型' ,
    attr_name VARCHAR(255)    COMMENT '属性名' ,
    attr_type BIGINT(20)    COMMENT '属性类型' ,
    column_comment VARCHAR(255)    COMMENT '说明' ,
    column_key VARCHAR(255)    COMMENT '是否为主键' ,
    extra VARCHAR(255)    COMMENT '策略' ,
    number INT(11)    COMMENT '序号' ,
    create_date DATETIME NOT NULL  DEFAULT 'CURRENT_TIMESTAMP' COMMENT '' ,
    update_date DATETIME   DEFAULT 'CURRENT_TIMESTAMP' COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '数据表';


CREATE UNIQUE INDEX PRIMARY ON g_column(id);

DROP TABLE IF EXISTS g_field_type;
CREATE TABLE g_field_type(
    id BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    column_type VARCHAR(255)    COMMENT '字段类型' ,
    attr_type VARCHAR(255)    COMMENT '数据类型' ,
    project VARCHAR(255)    COMMENT '包名' ,
    number INT(11)    COMMENT '排序' ,
    create_date DATETIME NOT NULL  DEFAULT 'CURRENT_TIMESTAMP' COMMENT '' ,
    update_date DATETIME   DEFAULT 'CURRENT_TIMESTAMP' COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '字段类型关系';


CREATE UNIQUE INDEX PRIMARY ON g_field_type(id);

DROP TABLE IF EXISTS g_source;
CREATE TABLE g_source(
    id BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '' ,
    conn_name VARCHAR(255)    COMMENT '连接名称' ,
    conn_url VARCHAR(255)    COMMENT '连接信息' ,
    db_type INT(11)    COMMENT '数据库类型;0 MySQL' ,
    username VARCHAR(255)    COMMENT '用户名' ,
    password VARCHAR(255)    COMMENT '密码' ,
    status INT(11)    COMMENT '状态;0 启用 1 禁用' ,
    create_date DATETIME NOT NULL  DEFAULT 'CURRENT_TIMESTAMP' COMMENT '' ,
    update_date DATETIME   DEFAULT 'CURRENT_TIMESTAMP' COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '数据源';


CREATE UNIQUE INDEX PRIMARY ON g_source(id);

DROP TABLE IF EXISTS g_table;
CREATE TABLE g_table(
    id BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '' ,
    source_id BIGINT(20)    COMMENT '数据源id' ,
    table_name VARCHAR(255)    COMMENT '表名' ,
    engine VARCHAR(255)    COMMENT '存储引擎' ,
    table_comment VARCHAR(255)    COMMENT '注释' ,
    class_name VARCHAR(255)    COMMENT '类名' ,
    base_class_id BIGINT(20)    COMMENT '基类' ,
    module_name VARCHAR(255)    COMMENT '模块名' ,
    sub_module_name VARCHAR(255)    COMMENT '子模块名' ,
    package_name VARCHAR(255)    COMMENT '包名' ,
    create_time DATETIME    COMMENT '表创建的时间' ,
    create_date DATETIME NOT NULL  DEFAULT 'CURRENT_TIMESTAMP' COMMENT '' ,
    update_date DATETIME   DEFAULT 'CURRENT_TIMESTAMP' COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '数据表';


CREATE UNIQUE INDEX PRIMARY ON g_table(id);

DROP TABLE IF EXISTS g_template;
CREATE TABLE g_template(
    id BIGINT(20) NOT NULL AUTO_INCREMENT  COMMENT '' ,
    name VARCHAR(255)    COMMENT '模板名' ,
    file_name VARCHAR(255)    COMMENT '文件名' ,
    content TEXT    COMMENT '模板内容' ,
    path VARCHAR(255)    COMMENT '生成路径' ,
    status INT(11)    COMMENT '状态;0 启用 1 禁用' ,
    create_date DATETIME NOT NULL  DEFAULT 'CURRENT_TIMESTAMP' COMMENT '' ,
    update_date DATETIME   DEFAULT 'CURRENT_TIMESTAMP' COMMENT '' ,
    PRIMARY KEY (id)
)  COMMENT = '模板';


CREATE UNIQUE INDEX PRIMARY ON g_template(id);


INSERT INTO `g_source`(`id`, `conn_name`, `conn_url`, `db_type`, `username`, `password`, `status`, `create_date`, `update_date`) VALUES (1, '本地MySQL', 'jdbc:mysql://localhost:3306/generator?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai', 0, 'root', '123456', 0, '2022-02-22 11:16:58', '2022-03-06 15:48:36');
INSERT INTO `g_table`(`id`, `source_id`, `table_name`, `engine`, `table_comment`, `class_name`, `base_class_id`, `module_name`, `sub_module_name`, `package_name`, `create_time`, `create_date`, `update_date`) VALUES (38, 1, 'g_source', 'InnoDB', '数据源', 'Source', NULL, NULL, NULL, 'com.susu.generator', NULL, '2022-05-13 10:20:22', '2022-05-13 10:20:44');
INSERT INTO `g_template`(`id`, `name`, `file_name`, `content`, `path`, `status`, `create_date`, `update_date`) VALUES (1, 'Entity.java', '${className}Entity.java', 'package ${packageName}<#if moduleName??>.${moduleName}</#if><#if subModuleName??>.${subModuleName}</#if>;\n\nimport lombok.Data;\nimport lombok.EqualsAndHashCode;\nimport com.baomidou.mybatisplus.annotation.*;\n<#list projectList as i>\nimport ${i!};\n</#list>\n\n/**\n * ${tableComment}\n */\n@Data\n@EqualsAndHashCode(callSuper=false)\n@TableName(\"${tableName}\")\npublic class ${className}Entity{\n  \n<#list columnList as column>\n  	<#if column.columnComment!?length gt 0>\n	/**\n	* ${column.columnComment}\n	*/\n	</#if>\n   	private ${column.attrTypeName} ${column.attrName};\n	  \n</#list>\n}', 'src/main/java/com/susu/generator/${moduleName}/${subModuleName}/entity/', 0, '2022-02-22 11:14:42', '2022-05-13 12:07:49');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (178, 34, 'id', 'bigint', 'id', 42, '', 'PRI', 'auto_increment', 0, '2022-05-11 17:55:45', '2022-05-11 17:55:45');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (179, 34, 'conn_name', 'varchar', 'connname', 38, '连接名称', '', '', 1, '2022-05-11 17:55:45', '2022-05-11 17:55:45');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (180, 34, 'conn_url', 'varchar', 'connurl', 38, '连接信息', '', '', 2, '2022-05-11 17:55:45', '2022-05-11 17:55:45');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (181, 34, 'db_type', 'int', 'dbtype', 40, '数据库类型 0 MySQL', '', '', 3, '2022-05-11 17:55:45', '2022-05-11 17:55:45');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (182, 34, 'username', 'varchar', 'username', 38, '用户名', '', '', 4, '2022-05-11 17:55:45', '2022-05-11 17:55:45');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (183, 34, 'password', 'varchar', 'password', 38, '密码', '', '', 5, '2022-05-11 17:55:45', '2022-05-11 17:55:45');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (184, 34, 'status', 'int', 'status', 40, '状态 0 启用 1 禁用', '', '', 6, '2022-05-11 17:55:45', '2022-05-11 17:55:45');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (185, 34, 'create_date', 'datetime', 'createdate', 41, '', '', 'DEFAULT_GENERATED', 7, '2022-05-11 17:55:45', '2022-05-11 17:55:45');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (186, 34, 'update_date', 'datetime', 'updatedate', 41, '', '', 'DEFAULT_GENERATED on update CURRENT_TIMESTAMP', 8, '2022-05-11 17:55:45', '2022-05-11 17:55:45');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (199, 36, 'id', 'bigint', 'id', 42, '', 'PRI', 'auto_increment', 0, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (200, 36, 'source_id', 'bigint', 'sourceId', 42, '数据源id', '', '', 1, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (201, 36, 'table_name', 'varchar', 'tableName', 38, '表名', '', '', 2, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (202, 36, 'engine', 'varchar', 'engine', 38, '存储引擎', '', '', 3, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (203, 36, 'table_comment', 'varchar', 'tableComment', 38, '注释', '', '', 4, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (204, 36, 'class_name', 'varchar', 'className', 38, '类名', '', '', 5, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (205, 36, 'base_class_id', 'bigint', 'baseClassId', 42, '基类', '', '', 6, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (206, 36, 'module_name', 'varchar', 'moduleName', 38, '模块名', '', '', 7, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (207, 36, 'sub_module_name', 'varchar', 'subModuleName', 38, '子模块名', '', '', 8, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (208, 36, 'package_name', 'varchar', 'packageName', 38, '包名', '', '', 9, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (209, 36, 'backend_path', 'varchar', 'backendPath', 38, '生成路径', '', '', 10, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (210, 36, 'create_time', 'datetime', 'createTime', 41, '表创建的时间', '', '', 11, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (211, 36, 'create_date', 'datetime', 'createDate', 41, '', '', 'DEFAULT_GENERATED', 12, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (212, 36, 'update_date', 'datetime', 'updateDate', 41, '', '', 'DEFAULT_GENERATED on update CURRENT_TIMESTAMP', 13, '2022-05-12 00:04:57', '2022-05-12 00:04:57');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (213, 37, 'id', 'bigint', 'id', 42, '', 'PRI', 'auto_increment', 0, '2022-05-12 00:05:04', '2022-05-12 00:05:04');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (214, 37, 'name', 'varchar', 'name', 38, '模板名', '', '', 1, '2022-05-12 00:05:04', '2022-05-12 00:05:04');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (215, 37, 'file_name', 'varchar', 'fileName', 38, '文件名', '', '', 2, '2022-05-12 00:05:04', '2022-05-12 00:05:04');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (216, 37, 'content', 'text', 'content', 55, '模板内容', '', '', 3, '2022-05-12 00:05:04', '2022-05-12 00:05:04');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (217, 37, 'path', 'varchar', 'path', 38, '生成路径', '', '', 4, '2022-05-12 00:05:04', '2022-05-12 00:05:04');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (218, 37, 'status', 'int', 'status', 40, '状态 0 启用 1 禁用', '', '', 5, '2022-05-12 00:05:04', '2022-05-12 00:05:04');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (219, 37, 'create_date', 'datetime', 'createDate', 41, '', '', 'DEFAULT_GENERATED', 6, '2022-05-12 00:05:04', '2022-05-12 00:05:04');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (220, 37, 'update_date', 'datetime', 'updateDate', 41, '', '', 'DEFAULT_GENERATED on update CURRENT_TIMESTAMP', 7, '2022-05-12 00:05:04', '2022-05-12 00:05:04');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (221, 38, 'id', 'bigint', 'id', 42, '', 'PRI', 'auto_increment', 0, '2022-05-13 10:20:22', '2022-05-13 10:20:22');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (222, 38, 'conn_name', 'varchar', 'connName', 38, '连接名称', '', '', 1, '2022-05-13 10:20:22', '2022-05-13 10:20:22');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (223, 38, 'conn_url', 'varchar', 'connUrl', 38, '连接信息', '', '', 2, '2022-05-13 10:20:22', '2022-05-13 10:20:22');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (224, 38, 'db_type', 'int', 'dbType', 40, '数据库类型 0 MySQL', '', '', 3, '2022-05-13 10:20:22', '2022-05-13 10:20:22');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (225, 38, 'username', 'varchar', 'username', 38, '用户名', '', '', 4, '2022-05-13 10:20:22', '2022-05-13 10:20:22');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (226, 38, 'password', 'varchar', 'password', 38, '密码', '', '', 5, '2022-05-13 10:20:22', '2022-05-13 10:20:22');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (227, 38, 'status', 'int', 'status', 40, '状态 0 启用 1 禁用', '', '', 6, '2022-05-13 10:20:22', '2022-05-13 10:20:22');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (228, 38, 'create_date', 'datetime', 'createDate', 41, '', '', 'DEFAULT_GENERATED', 7, '2022-05-13 10:20:22', '2022-05-13 10:20:22');
INSERT INTO `g_column`(`id`, `table_id`, `column_name`, `column_type`, `attr_name`, `attr_type`, `column_comment`, `column_key`, `extra`, `number`, `create_date`, `update_date`) VALUES (229, 38, 'update_date', 'datetime', 'updateDate', 41, '', '', 'DEFAULT_GENERATED on update CURRENT_TIMESTAMP', 8, '2022-05-13 10:20:22', '2022-05-13 10:20:22');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (38, 'varchar', 'String', '', 1, '2022-03-20 01:35:21', '2022-05-11 22:41:22');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (40, 'int', 'Integer', NULL, 3, '2022-03-24 17:06:58', '2022-03-24 17:06:58');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (41, 'datetime', 'Date', 'java.util.Date', 4, '2022-03-24 17:08:36', '2022-03-24 17:08:36');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (42, 'bigint', 'Long', NULL, 5, '2022-03-24 17:09:08', '2022-03-24 17:09:08');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (43, 'tinyint', 'Integer', NULL, 6, '2022-05-11 22:22:42', '2022-05-11 22:22:42');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (44, 'smallint', 'Integer', NULL, 7, '2022-05-11 22:22:52', '2022-05-11 22:23:23');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (45, 'char', 'String', '', 12, '2022-05-11 22:23:01', '2022-05-11 22:25:02');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (49, 'decimal', 'BigDecimal', 'java.math.BigDecimal', 2, '2022-05-11 22:40:32', '2022-05-11 22:42:48');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (50, 'float', 'Float', NULL, 8, '2022-05-11 22:44:10', '2022-05-11 22:44:10');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (51, 'double', 'Double', NULL, 9, '2022-05-11 22:44:20', '2022-05-11 22:44:20');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (52, 'longtext', 'String', NULL, 13, '2022-05-11 22:51:22', '2022-05-11 22:57:05');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (53, 'date', 'Date', 'java.util.Date', 15, '2022-05-11 22:54:56', '2022-05-11 22:57:49');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (54, 'timestamp', 'Date', 'java.util.Date', 14, '2022-05-11 22:56:36', '2022-05-11 22:57:16');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (55, 'text', 'String', NULL, 16, '2022-05-11 22:57:31', '2022-05-11 22:57:44');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (56, 'tinytext', 'String', NULL, 10, '2022-05-11 22:58:10', '2022-05-11 22:58:10');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (57, 'bit', 'Boolean', NULL, 11, '2022-05-11 22:58:34', '2022-05-11 22:58:34');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (58, 'NUMBER', 'Integer', NULL, 17, '2022-05-11 22:59:17', '2022-05-11 22:59:17');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (59, 'BINARY_INTEGER', 'Integer', NULL, 18, '2022-05-11 23:08:22', '2022-05-11 23:08:22');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (60, 'LONG', 'String', NULL, 19, '2022-05-11 23:08:34', '2022-05-11 23:08:34');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (61, 'BINARY_FLOAT', 'Float', NULL, 20, '2022-05-11 23:08:52', '2022-05-11 23:08:52');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (62, 'BINARY_DOUBLE', 'Double', NULL, 21, '2022-05-11 23:09:04', '2022-05-11 23:09:04');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (63, 'VARCHAR2', 'String', NULL, 22, '2022-05-11 23:09:26', '2022-05-11 23:09:37');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (64, 'NVARCHAR', 'String', NULL, 23, '2022-05-11 23:09:48', '2022-05-11 23:09:48');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (65, 'NVARCHAR2', 'String', NULL, 24, '2022-05-11 23:09:57', '2022-05-11 23:09:57');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (66, 'CLOB', 'String', NULL, 25, '2022-05-11 23:10:07', '2022-05-11 23:10:07');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (67, 'BLOB', 'String', NULL, 26, '2022-05-11 23:10:16', '2022-05-11 23:10:16');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (68, 'TIMESTAMP(6)', 'Date', 'java.util.Date', 27, '2022-05-11 23:10:29', '2022-05-11 23:10:39');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (69, 'int8', 'Long', NULL, 28, '2022-05-11 23:10:52', '2022-05-11 23:10:52');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (70, 'int4', 'Integer', NULL, 29, '2022-05-11 23:11:01', '2022-05-11 23:11:01');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (71, 'int2', 'Integer', NULL, 30, '2022-05-11 23:11:14', '2022-05-11 23:11:14');
INSERT INTO `g_field_type`(`id`, `column_type`, `attr_type`, `project`, `number`, `create_date`, `update_date`) VALUES (72, 'numeric', 'BigDecimal', 'java.math.BigDecimal', 31, '2022-05-11 23:11:31', '2022-05-11 23:11:31');


