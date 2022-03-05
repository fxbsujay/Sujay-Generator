package com.susu.generator.common.base;

import com.susu.generator.common.Query;
import com.susu.generator.entity.SourceEntity;

import java.util.List;

public interface BaseDao<T> {

    List<T> queryList(Query query);

    T selectById(Long id);

    int insert(T entity);

    int updateById(T entity);

    int deleteById(Long id);
}
