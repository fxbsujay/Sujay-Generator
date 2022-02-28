package com.susu.generator.controller;


import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.entity.SourceEntity;
import com.susu.generator.service.SourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/source")
public class SourceController {

    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }


    /**
     * 列表
     */
    @GetMapping("/page")
    public Result list(@RequestParam Map<String, Object> params){
        PageData<SourceEntity> page = sourceService.page(new Query(params));
        return Result.ok(page);
    }

}
