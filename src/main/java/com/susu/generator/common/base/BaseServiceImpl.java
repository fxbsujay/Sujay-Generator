package com.susu.generator.common.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.susu.generator.common.ConvertUtils;
import com.susu.generator.common.PageData;
import com.susu.generator.common.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * <p> CRUD基层服务类 </p>
 * @author fxbsujay@gmail.com
 * @version  17:36 2022/3/5
 */
public abstract class BaseServiceImpl <M extends BaseDao<T>, T extends BaseEntity, D> implements BaseService<T, D>{

    @Autowired
    protected M baseDao;

    @Override
    public PageData<D> page(Query query) {
        Page<T> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<T> list = baseDao.queryList(query);
        int total = (int) page.getTotal();
        return new PageData<>(ConvertUtils.sourceToTarget(list, currentDtoClass()),total);
    }

    @Override
    public List<D> list(Query query) {
        List<T> list = baseDao.queryList(query);
        return ConvertUtils.sourceToTarget(list, currentDtoClass());
    }

    @Override
    public D info(Long id) {
        T entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity,currentDtoClass());
    }

    @Override
    public Boolean save(D dto) {
        T entity = ConvertUtils.sourceToTarget(dto, currentEntityClass());
        baseDao.insert(entity);
        BeanUtils.copyProperties(entity, dto);
        return true;
    }

    @Override
    public Boolean update(D dto) {
        T entity = ConvertUtils.sourceToTarget(dto, currentEntityClass());
        baseDao.updateById(entity);
        return true;
    }

    @Override
    public Boolean delete(Long id) {
         baseDao.deleteById(id);
         return true;
    }

    @Override
    public int delete(Long[] ids) {
        return baseDao.deleteBatch(ids);
    }

    protected Class<D> currentDtoClass() {
        return (Class<D>) getSuperClassGenericType(getClass(), 2);
    }

    protected Class<T> currentEntityClass() {
        return (Class<T>) getSuperClassGenericType(getClass(), 1);
    }

    public static Class<?> getSuperClassGenericType(final Class<?> clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) params[index];
    }


}
