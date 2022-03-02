package com.susu.generator.dto;

import com.susu.generator.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fxbsujay@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SourceDTO extends BaseEntity {

    /**
     * 连接名称
     **/
    private String connName;

    /**
     * 连接地址
     **/
    private String connUrl;

    /**
     * 数据库类型 0 MySql
     **/
    private Integer dbType;

    /**
     * 用户名
     **/
    private String username;

    /**
     * 密码
     **/
    private String password;

    /**
     * 状态 0 启用 1 禁用
     **/
    private Integer status;

}
