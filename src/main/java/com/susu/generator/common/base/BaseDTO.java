package com.susu.generator.common.base;

import lombok.Data;

import java.util.Date;

/**
 * @author  sujay
 * @version  19:34 2022/3/5
 */
@Data
public class BaseDTO {

    private Long id;

    private Date createDate;

    private Date updateDate;
}
