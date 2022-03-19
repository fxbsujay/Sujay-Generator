package com.susu.generator.controller;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.dto.TemplateDTO;
import com.susu.generator.service.TemplateService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template")
public class TemplateController {

    private final TemplateService service;

    public TemplateController(TemplateService service) {
        this.service = service;
    }

    /**
     * 列表
     */
    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params){
        PageData<TemplateDTO> page = service.page(new Query(params));
        return Result.ok(page);
    }

    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        List<TemplateDTO> list = service.list(new Query(params));
        return Result.ok(list);
    }

    @GetMapping("{id}")
    public Result info(@PathVariable("id") Long id){
        TemplateDTO dto = service.info(id);
        return Result.ok(dto);
    }

    @PostMapping
    public Result save(@RequestBody TemplateDTO dto) {
        Boolean flag = service.save(dto);
        if (!flag) {
            return Result.error();
        }
        return Result.ok();
    }

    @PutMapping
    public Result update(@RequestBody TemplateDTO dto) {
        Boolean flag = service.update(dto);
        if (!flag) {
            return Result.error();
        }
        return Result.ok();
    }

    @DeleteMapping
    public Result delete(@RequestBody Long[] id) {
        service.delete(id);
        return Result.ok();
    }
}
