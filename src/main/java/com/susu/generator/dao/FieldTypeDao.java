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

    /**
     * 查询同数据库类型
     * @param columnType 数据库字段类型
     * @return 数量
     */
    int countSizeByColumnType(@Param("columnType") String columnType);

    /**
     * 查询除本条信息以外的同数据库类型
     * @param columnType 数据库字段类型
     * @param id 主键
     * @return 数量
     */
    int countSizeByColumnTypeAndExId(@Param("columnType")String columnType,@Param("id") Long id);
}
