package com.susu.generator.dao;

import com.susu.generator.common.base.BaseDao;
import com.susu.generator.entity.TableEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableDao extends BaseDao<TableEntity> {

}
