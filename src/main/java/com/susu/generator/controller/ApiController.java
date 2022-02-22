package com.susu.generator.controller;

import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


    /**
     * 生成代码
     */
    @RequestMapping("{id}")
    public void code(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        byte[] data = generatorService.generate(id);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

}
