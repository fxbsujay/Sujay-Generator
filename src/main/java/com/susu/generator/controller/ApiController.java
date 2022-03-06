package com.susu.generator.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.susu.generator.common.Query;
import com.susu.generator.common.Result;
import com.susu.generator.config.DynamicDataSourceConfig;
import com.susu.generator.entity.TableEntity;
import com.susu.generator.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        List<TableEntity> tableEntities = generatorService.queryTableList(new Query(params));

        return Result.ok(tableEntities);
    }

    @GetMapping("test")
    public Result test1(){

        DruidDataSource druidDataSource = new DruidDataSource();
        //新的地址
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/springcloud?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
        //用户
        druidDataSource.setUsername("root");
        //密码
        druidDataSource.setPassword("123456");
        DynamicDataSourceConfig.dataSourcesMap.put("dbkey", druidDataSource);
        DynamicDataSourceConfig.setDataSource("dbkey");
        Map<String, Object> params = new HashMap<>();
        System.out.println(DynamicDataSourceConfig.dataSourcesMap.toString());
        params.put("page","1");
        params.put("limit","10");
        List<TableEntity> tableEntities = generatorService.queryTableList(new Query(params));
        return Result.ok(tableEntities);
    }

}
