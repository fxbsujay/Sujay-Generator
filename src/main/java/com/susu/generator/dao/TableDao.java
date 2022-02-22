package com.susu.generator.dao;

import com.susu.generator.common.Query;
import com.susu.generator.entity.TableEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableDao {

    List<TableEntity> queryList(Query query);

    TableEntity selectById(Long id);
}
