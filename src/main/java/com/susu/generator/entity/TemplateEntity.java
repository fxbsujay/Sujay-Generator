package com.susu.generator.entity;

import com.susu.generator.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

/**
 * <p> 模板 </p>
 * @author fxbsujay@gmail.com
 * @version 19:39 2022/3/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模板名称
     **/
    private String name;

    /**
     * 文件名称
     **/
    private String fileName;

    /**
     * 模板内容
     **/
    private String content;

    /**
     * 生成路径
     **/
    private String path;

    /**
     * 状态 0 启用 1 禁用
     **/
    private Integer status;

}
