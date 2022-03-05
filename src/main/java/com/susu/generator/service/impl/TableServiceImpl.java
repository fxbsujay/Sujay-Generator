package com.susu.generator.service.impl;

import com.susu.generator.common.base.BaseServiceImpl;
import com.susu.generator.dao.TableDao;
import com.susu.generator.dto.TableDTO;
import com.susu.generator.entity.TableEntity;
import com.susu.generator.service.TableService;
import org.springframework.stereotype.Service;

/**
 * @author 26933
 */
@Service
public class TableServiceImpl  extends BaseServiceImpl<TableDao, TableEntity, TableDTO> implements TableService {

}
