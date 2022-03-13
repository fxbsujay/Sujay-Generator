package com.susu.generator.service;

import com.susu.generator.common.base.BaseService;
import com.susu.generator.dto.TableDTO;
import com.susu.generator.entity.TableEntity;

import java.util.List;

/**
 * @author fxbsujay@gmail.com
 */
public interface TableService extends BaseService<TableEntity, TableDTO> {

    /**
     * 查询数据源下的所有表表
     *
     * @param sourceId 数据源Id
     * @return Result<TableDTO>
     */
    List<TableDTO> selectTableListBySourceId(Long sourceId);

    /**
     * 从数据源中导入这个表
     *
     * @param dto 表名，数据源
     */
    void importTable(TableDTO dto);
}
