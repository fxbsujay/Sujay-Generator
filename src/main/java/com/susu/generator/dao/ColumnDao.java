package com.susu.generator.dao;

import com.susu.generator.common.base.BaseDao;
import com.susu.generator.entity.ColumnEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ColumnDao extends BaseDao<ColumnEntity> {

    /**
     * 根据表id查询对应字段信息
     * @param tableId 表id
     * @return 字段信息数组
     */
    List<ColumnEntity> queryListByTableId(@Param("tableId") Long tableId);
}
