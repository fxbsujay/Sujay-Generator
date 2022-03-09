package com.susu.generator.controller;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.dto.TableDTO;
import com.susu.generator.service.TableService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/table")
public class TableController {

    private final TableService service;

    public TableController(TableService service) {
        this.service = service;
    }

    /**
     * 列表
     */
    @GetMapping("/page")
    public Result page(@RequestParam Map<String, Object> params){
        PageData<TableDTO> page = service.page(new Query(params));
        return Result.ok(page);
    }

    @GetMapping("{id}")
    public Result info(@PathVariable("id") Long id){
        TableDTO dto = service.info(id);
        return Result.ok(dto);
    }

    @PostMapping
    public Result save(@RequestBody TableDTO dto) {
        Boolean flag = service.save(dto);
        if (!flag) {
            return Result.error();
        }
        return Result.ok();
    }

    @PutMapping
    public Result update(@RequestBody TableDTO dto) {
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

    /**
     * <p> 查询数据库的所有表 </p>
     * @author SuJay
     * @date 13:15 2022/3/8
     * @param id: 数据源id
     * @return com.susu.generator.common.Result
     **/
    @GetMapping("/queryTableListBySourceId/{id}")
    public Result selectTableListBySourceId(@PathVariable("id") Long id){
        List<TableDTO> list = service.selectTableListBySourceId(id);
        return Result.ok(list);
    }


}
