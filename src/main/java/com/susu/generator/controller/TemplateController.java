package com.susu.generator.controller;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.entity.TemplateEntity;
import com.susu.generator.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    public TemplateService templateService;

    /**
     * 列表
     */
    @GetMapping("/page")
    public Result list(@RequestParam Map<String, Object> params){
        PageData<TemplateEntity> page = templateService.page(new Query(params));
        return Result.ok(page);
    }

    @GetMapping("{id}")
    public Result list(@PathVariable("id") Long id){
        TemplateEntity info = templateService.info(id);
        return Result.ok(info);
    }
}
