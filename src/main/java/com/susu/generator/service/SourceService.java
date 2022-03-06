package com.susu.generator.service;

import com.susu.generator.common.base.BaseService;
import com.susu.generator.dto.SourceDTO;
import com.susu.generator.entity.SourceEntity;

/**
 * @author fxbsujay@gmail.com
 */
public interface SourceService extends BaseService<SourceEntity, SourceDTO> {

    /**
     * 数据源连接测试
     * @param id 数据源id
     * @return true/false 成功 / 失败
     */
    Boolean connTest(Long id);
}
