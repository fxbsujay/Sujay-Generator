package com.susu.generator.service.impl;

import com.susu.generator.common.base.BaseServiceImpl;
import com.susu.generator.dao.FieldTypeDao;
import com.susu.generator.dto.FieldTypeDTO;
import com.susu.generator.entity.FieldTypeEntity;
import com.susu.generator.exception.GeneratorException;
import com.susu.generator.service.FieldTypeService;
import org.springframework.stereotype.Service;

/**
 * @author fxbsujay@gmail.com
 */
@Service
public class FieldTypeServiceImpl extends BaseServiceImpl<FieldTypeDao, FieldTypeEntity, FieldTypeDTO> implements FieldTypeService {


    @Override
    public Boolean save(FieldTypeDTO dto) {
        int count = baseDao.countSizeByColumnType(dto.getColumnType());
        if (count > 0) {
            throw new GeneratorException("已存在同字段类型的信息：【 "+ dto.getColumnType() +" 】");
        }
        return super.save(dto);
    }

    @Override
    public Boolean update(FieldTypeDTO dto) {
        int count = baseDao.countSizeByColumnTypeAndExId(dto.getColumnType(),dto.getId());
        if (count > 0) {
            throw new GeneratorException("已存在同字段类型的信息：【 "+ dto.getColumnType() +" 】");
        }
        return super.update(dto);
    }
}
