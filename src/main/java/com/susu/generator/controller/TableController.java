package com.susu.generator.controller;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.service.GeneratorService;
import com.susu.generator.service.TableService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    public TableService tableService;

    /**
     * 列表
     */
    @GetMapping("/page")
    public Result list(@RequestParam Map<String, Object> params){
        PageData page = tableService.page(new Query(params));
        return Result.ok(page);
    }


}
