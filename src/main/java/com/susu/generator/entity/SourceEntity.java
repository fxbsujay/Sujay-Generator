package com.susu.generator.entity;

import com.susu.generator.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * <p> 数据源 </p>
 * @author fxbsujay@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SourceEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
