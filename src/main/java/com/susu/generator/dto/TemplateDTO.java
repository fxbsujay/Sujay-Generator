package com.susu.generator.dto;

import com.susu.generator.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateDTO extends BaseEntity {

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
