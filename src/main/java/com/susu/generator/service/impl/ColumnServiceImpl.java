package com.susu.generator.service.impl;

import com.susu.generator.common.base.BaseServiceImpl;
import com.susu.generator.dao.ColumnDao;
import com.susu.generator.dto.ColumnDTO;
import com.susu.generator.entity.ColumnEntity;
import com.susu.generator.service.ColumnService;
import org.springframework.stereotype.Service;

/**
 * @author fxbsujay@gmail.com
 */
@Service
public class ColumnServiceImpl extends BaseServiceImpl<ColumnDao, ColumnEntity, ColumnDTO> implements ColumnService {

}
