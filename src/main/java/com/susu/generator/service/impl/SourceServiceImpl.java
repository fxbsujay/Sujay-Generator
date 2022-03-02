package com.susu.generator.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.susu.generator.common.ConvertUtils;
import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.dao.SourceDao;
import com.susu.generator.dto.SourceDTO;
import com.susu.generator.entity.SourceEntity;
import com.susu.generator.service.SourceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author fxbsujay@gmail.com
 */
@Service
public class SourceServiceImpl  implements SourceService {

    @Resource
    private SourceDao sourceDao;

    @Override
    public PageData<SourceDTO> page(Query query) {
        Page<SourceEntity> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<SourceEntity> list = sourceDao.queryList(query);
        int total = (int) page.getTotal();
        return new PageData<>(ConvertUtils.sourceToTarget(list,SourceDTO.class),total);
    }

    @Override
    public SourceDTO info(Long id) {
        return  ConvertUtils.sourceToTarget(sourceDao.selectById(id),SourceDTO.class);
    }
}
