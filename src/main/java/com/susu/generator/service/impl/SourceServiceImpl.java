package com.susu.generator.service.impl;

import com.susu.generator.common.ConvertUtils;
import com.susu.generator.common.DBUtils;
import com.susu.generator.common.base.BaseServiceImpl;
import com.susu.generator.dao.SourceDao;
import com.susu.generator.dto.SourceDTO;
import com.susu.generator.entity.SourceEntity;
import com.susu.generator.service.SourceService;
import org.springframework.stereotype.Service;

/**
 * @author fxbsujay@gmail.com
 */
@Service
public class SourceServiceImpl extends BaseServiceImpl<SourceDao,SourceEntity,SourceDTO> implements SourceService {

    @Override
    public Boolean connTest(Long id) {
        SourceEntity entity = baseDao.selectById(id);
        if (entity == null) {
            return false;
        }
        SourceDTO dto = ConvertUtils.sourceToTarget(entity, SourceDTO.class);
        return DBUtils.connTest(dto);
    }
}
