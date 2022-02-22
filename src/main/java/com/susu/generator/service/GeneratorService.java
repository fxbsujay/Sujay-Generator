package com.susu.generator.service;


import com.susu.generator.entity.ColumnEntity;
import com.susu.generator.entity.TableEntity;
import java.util.List;

public interface GeneratorService{

    TableEntity queryTable(String tableName);

    List<ColumnEntity> queryColumns(String tableName);

    byte[] generate(Long id);
}
