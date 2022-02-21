package com.susu.generator.controller;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/generator")
public class ApiController {


    @Autowired
    public GeneratorService generatorService;

    /**
     * 列表
     */
    @GetMapping("/page")
    public Result list(@RequestParam Map<String, Object> params){
        PageData page = generatorService.page(new Query(params));
        return Result.ok(page);
    }




}
