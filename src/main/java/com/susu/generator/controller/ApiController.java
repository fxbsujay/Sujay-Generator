package com.susu.generator.controller;

import com.susu.generator.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/generator")
public class ApiController {

    @Autowired
    public GeneratorService generatorService;

    /**
     * 生成代码
     */
    @RequestMapping("{id}")
    public void code(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        byte[] data = generatorService.generate(id);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"generator.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

}
