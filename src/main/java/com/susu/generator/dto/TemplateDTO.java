package com.susu.generator.dto;

import com.susu.generator.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateDTO extends BaseDTO implements Serializable {

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
