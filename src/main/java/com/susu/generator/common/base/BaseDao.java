package com.susu.generator.common.base;

import com.susu.generator.common.Query;

import java.util.List;

public interface BaseDao<T> {

    List<T> queryList(Query query);

    T selectById(Long id);
}
