package com.susu.generator.entity;

import com.susu.generator.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * <p>  Description: 字段类型 </p>
 * @author fxbsujay@gmail.com
 * @version  0:04 2022/3/20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FieldTypeEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段类型
     **/
    private String columnType;

    /**
     * 属性类型
     **/
    private String attrType;

    /**
     * 所在包
     **/
    private String project;

    /**
     * 序号
     **/
    private Integer number;

}
