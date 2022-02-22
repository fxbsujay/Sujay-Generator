package com.susu.generator.common.base;


import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private Long id;

    private Date createDate;

    private Date updateDate;

}
