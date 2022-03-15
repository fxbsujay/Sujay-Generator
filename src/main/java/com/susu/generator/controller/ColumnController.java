package com.susu.generator.controller;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.dto.ColumnDTO;
import com.susu.generator.service.ColumnService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @author fxbsujay@gmail.com
 * <p>Description 字段信息 </p>
 * @version  23:59 2022/3/15
 */
@RestController
@RequestMapping("column")
public class ColumnController {

    private final ColumnService service;

    public ColumnController(ColumnService service) {
        this.service = service;
    }

    /**
     * 列表
     */
    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params){
        PageData<ColumnDTO> page = service.page(new Query(params));
        return Result.ok(page);
    }

    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        List<ColumnDTO> list = service.list(new Query(params));
        return Result.ok(list);
    }

    @GetMapping("{id}")
    public Result info(@PathVariable("id") Long id){
        ColumnDTO dto = service.info(id);
        return Result.ok(dto);
    }

    @PostMapping
    public Result save(@RequestBody ColumnDTO dto) {
        Boolean flag = service.save(dto);
        if (!flag) {
            return Result.error();
        }
        return Result.ok();
    }

    @PutMapping
    public Result update(@RequestBody ColumnDTO dto) {
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
