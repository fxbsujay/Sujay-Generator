package com.susu.generator.controller;


import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.dto.FieldTypeDTO;
import com.susu.generator.service.FieldTypeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/field-type")
public class FieldTypeController {

    private final FieldTypeService service;

    public FieldTypeController(FieldTypeService service) {
        this.service = service;
    }

    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params){
        PageData<FieldTypeDTO> page = service.page(new Query(params));
        return Result.ok(page);
    }

    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        List<FieldTypeDTO> list = service.list(new Query(params));
        return Result.ok(list);
    }

    @GetMapping("{id}")
    public Result info(@PathVariable("id") Long id){
        FieldTypeDTO dto = service.info(id);
        return Result.ok(dto);
    }

    @PostMapping
    public Result save(@RequestBody FieldTypeDTO dto) {
        Boolean flag = service.save(dto);
        if (!flag) {
            return Result.error();
        }
        return Result.ok();
    }

    @PutMapping
    public Result update(@RequestBody FieldTypeDTO dto) {
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
