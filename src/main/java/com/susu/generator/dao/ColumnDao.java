package com.susu.generator.dao;

import com.susu.generator.common.base.BaseDao;
import com.susu.generator.entity.ColumnEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ColumnDao extends BaseDao<ColumnEntity> {

    void createTable(@Param("tableName") String tableName);
}
