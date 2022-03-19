package com.susu.generator.common.base;

import com.susu.generator.common.Query;
import com.susu.generator.entity.SourceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fxbsujay@gmail.com
 */
public interface BaseDao<T> {

    /**
     * 查询列表
     * @param query 查询条件
     * @return 集合
     */
    List<T> queryList(Query query);

    /**
     * 查询单条
     * @param id  对象主键
     * @return 实体对象
     */
    T selectById(@Param("id") Long id);

    /**
     * 新增
     * @param entity 实体对象
     * @return 影响条数
     */
    int insert(T entity);

    /**
     * 新增
     * @param list 实体对象
     * @return 影响条数
     */
    int insertBatch(@Param("list") List<T> list);

    /**
     * 修改
     * @param query 修改条件
     * @param entity 实体对象
     * @return 影响条数
     */
    int update(Query query,T entity);

    /**
     * 修改
     * @param entity 实体对象
     * @return 影响条数
     */
    int updateById(T entity);

    /**
     * 修改
     * @param list 实体对象
     * @return 影响条数
     */
    int updateBatch(@Param("list") List<T> list);

    /**
     * 删除
     * @param id 对象主键
     * @return 影响条数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 批量删除
     * @param ids 实体对象
     * @return 影响条数
     */
    int deleteBatch(@Param("ids") Long[] ids);

    /**
     * 删除
     * @param query 删除条件
     * @return 影响条数
     */
    int delete(Query query);
}
