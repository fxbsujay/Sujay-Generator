package com.susu.generator.service;


import com.susu.generator.entity.ColumnEntity;
import com.susu.generator.entity.TableEntity;
import java.util.List;
import java.util.Map;

public interface GeneratorService{

    List<TableEntity> queryTableList(Map<String, Object> map);

    TableEntity queryTable(String tableName);

    List<ColumnEntity> queryColumns(String tableName);

    byte[] generate(Long id);
}
