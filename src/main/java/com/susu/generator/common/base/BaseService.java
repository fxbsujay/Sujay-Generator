package com.susu.generator.common.base;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BaseService<T,D> {

    PageData<D> page(Query query);

    List<D> list(Query query);

    D info(Long id);

    Boolean save(D dto);

    Boolean update(D dto);

    Boolean delete(Long id);

    @Transactional
    int delete(Long[] ids);

}
