package com.susu.generator.dao;

import com.susu.generator.entity.ColumnEntity;
import com.susu.generator.entity.TableEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GeneratorDao {

    List<TableEntity> queryList(Map<String, Object> map);

    TableEntity queryTable(String tableName);

    List<ColumnEntity> queryColumns(String tableName);

}
