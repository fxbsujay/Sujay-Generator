package com.susu.generator.dao;

import com.susu.generator.common.base.BaseDao;
import com.susu.generator.entity.FieldTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface FieldTypeDao extends BaseDao<FieldTypeEntity> {

    /**
     * 查询
     * @param tableId 表id
     * @return project 包名数组
     */
    List<String> queryProjectListByTableId(@Param("tableId") Long tableId);
}
