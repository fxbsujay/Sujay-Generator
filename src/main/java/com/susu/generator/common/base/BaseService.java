package com.susu.generator.common.base;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;

public interface BaseService<T,D> {

    PageData<D> page(Query query);

    D info(Long id);
}
