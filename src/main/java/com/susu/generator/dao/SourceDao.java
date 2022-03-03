package com.susu.generator.dao;

import com.susu.generator.common.base.BaseDao;
import com.susu.generator.dto.SourceDTO;
import com.susu.generator.entity.SourceEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SourceDao extends BaseDao<SourceEntity> {

    int insert(SourceEntity entity);

    int updateById(SourceEntity entity);

    int deleteById(Long id);
}
