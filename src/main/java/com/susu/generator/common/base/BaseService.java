package com.susu.generator.common.base;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;

public interface BaseService<T> {

    PageData<T> page(Query query);

    T info(Long id);
}
