package com.susu.generator.service;

import com.susu.generator.common.PageData;

import java.util.Map;

public interface GeneratorService {

    PageData page(Map<String, Object> params);
}
