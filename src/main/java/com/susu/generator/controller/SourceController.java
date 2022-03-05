package com.susu.generator.controller;


import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.dto.SourceDTO;
import com.susu.generator.service.SourceService;
import org.springframework.web.bind.annotation.*;

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
    public Result page(@RequestParam Map<String, Object> params){
        PageData<SourceDTO> page = sourceService.page(new Query(params));
        return Result.ok(page);
    }

    @GetMapping("{id}")
    public Result info(@PathVariable("id") Long id){
        SourceDTO dto = sourceService.info(id);
        return Result.ok(dto);
    }

    @PostMapping
    public Result save(@RequestBody SourceDTO dto) {
        Boolean flag = sourceService.save(dto);
        if (!flag) {
            return Result.error();
        }
        return Result.ok();
    }

    @PutMapping
    public Result update(@RequestBody SourceDTO dto) {
        Boolean flag = sourceService.update(dto);
        if (!flag) {
            return Result.error();
        }
        return Result.ok();
    }

    @DeleteMapping
    public Result delete(@RequestBody Long[] id) {
        sourceService.delete(id);
        return Result.ok();
    }




}
