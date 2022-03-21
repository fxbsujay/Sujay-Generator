package com.susu.generator.service;

import com.susu.generator.common.base.BaseService;
import com.susu.generator.dto.ColumnDTO;
import com.susu.generator.entity.ColumnEntity;

/**
 * <p> Description: 字段关系 </p>
 * @author sujay
 * @version 0:01 2022/3/16
 */
public interface ColumnService extends BaseService<ColumnEntity, ColumnDTO> {
    ColumnDTO test();

}
