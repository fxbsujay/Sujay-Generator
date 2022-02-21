package com.susu.generator.service;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;

import java.util.Map;

public interface GeneratorService {

    PageData page(Query query);
}
