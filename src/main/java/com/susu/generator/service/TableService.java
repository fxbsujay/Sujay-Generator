package com.susu.generator.service;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;

public interface TableService {

    PageData page(Query query);
}
