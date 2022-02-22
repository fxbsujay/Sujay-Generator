package com.susu.generator.dao;

import com.susu.generator.common.base.BaseDao;
import com.susu.generator.entity.TemplateEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TemplateDao extends BaseDao<TemplateEntity> {

    List<TemplateEntity> selectAll();


}
