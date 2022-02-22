package com.susu.generator.service;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;

public interface GeneratorService{

    PageData page(Query query);

    byte[] generate(Long id);
}
