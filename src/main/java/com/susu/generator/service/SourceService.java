package com.susu.generator.service;

import com.susu.generator.common.base.BaseService;
import com.susu.generator.dto.SourceDTO;
import com.susu.generator.entity.SourceEntity;

/**
 * @author fxbsujay@gmail.com
 */
public interface SourceService extends BaseService<SourceEntity, SourceDTO> {


    Boolean save(SourceDTO dto);

    Boolean update(SourceDTO dto);

    Boolean delete(Long[] ids);

    SourceDTO selectById(Long id);
}
