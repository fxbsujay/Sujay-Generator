package com.susu.generator.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.dao.SourceDao;
import com.susu.generator.entity.SourceEntity;
import com.susu.generator.service.SourceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SourceServiceImpl  implements SourceService {

    @Resource
    private SourceDao sourceDao;

    @Override
    public PageData<SourceEntity> page(Query query) {
        Page<SourceEntity> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<SourceEntity> list = sourceDao.queryList(query);
        int total = (int) page.getTotal();
        return new PageData<>(list,total);
    }

    @Override
    public SourceEntity info(Long id) {
        return sourceDao.selectById(id);
    }
}
