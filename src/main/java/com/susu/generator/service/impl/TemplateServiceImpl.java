package com.susu.generator.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.dao.TemplateDao;
import com.susu.generator.entity.TemplateEntity;
import com.susu.generator.service.TemplateService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Resource
    private TemplateDao templateDao;

    @Override
    public PageData<TemplateEntity> page(Query query) {
        Page<TemplateEntity> page = PageHelper.startPage(query.getPage(), query.getLimit());

        List<TemplateEntity> list = templateDao.queryList(query);

        int total = (int) page.getTotal();
        return new PageData<>(list,total);
    }

    @Override
    public TemplateEntity info(Long id) {
        return templateDao.selectById(id);
    }
}
