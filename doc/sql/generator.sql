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

