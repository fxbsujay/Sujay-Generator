package com.susu.generator.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.susu.generator.common.ConvertUtils;
import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import com.susu.generator.dao.TableDao;
import com.susu.generator.dto.SourceDTO;
import com.susu.generator.dto.TableDTO;
import com.susu.generator.entity.TableEntity;
import com.susu.generator.service.TableService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 26933
 */
@Service
public class TableServiceImpl implements TableService {

    @Resource
    private TableDao tableDao;

    @Override
    public PageData<TableDTO> page(Query query) {

        Page<TableEntity> page = PageHelper.startPage(query.getPage(), query.getLimit());

        List<TableEntity> list = tableDao.queryList(query);

        int total = (int) page.getTotal();
        return new PageData<>(ConvertUtils.sourceToTarget(list, TableDTO.class),total);
    }

    @Override
    public TableDTO info(Long id) {
        return ConvertUtils.sourceToTarget(tableDao.selectById(id), TableDTO.class);
    }
}
