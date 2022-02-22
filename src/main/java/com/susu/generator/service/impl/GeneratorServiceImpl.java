package com.susu.generator.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.dao.GeneratorDao;
import com.susu.generator.dao.TableDao;
import com.susu.generator.entity.TableEntity;
import com.susu.generator.service.GeneratorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Resource
    private GeneratorDao generatorDao;

    @Resource
    private TableDao tableDao;

    @Override
    public PageData page(Query query) {
        Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String, Object>> list = generatorDao.queryList(query);
        int total = (int) page.getTotal();
        return new PageData(list,total);
    }

    @Override
    public byte[] generate(Long id) {

        TableEntity table = tableDao.selectById(id);

        return new byte[0];
    }
}
