package com.susu.generator.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 转换工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
public class ConvertUtils {

    public static boolean checkDTO(Object object){
        if (null == object) {
            return false;
        }
        try {
            for(Field f : object.getClass().getDeclaredFields()){
                f.setAccessible(true);
                if(f.get(object) == null || "".equals(f.get(object))){
                    return false;
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static <T> T sourceToTarget(Object source, Class<T> target){
        if(source == null){
            return null;
        }
        T targetObject = null;
        try {
            targetObject = target.newInstance();
            BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            log.error("convert error ", e);
        }

        return targetObject;
    }

    public static <T> List<T> sourceToTarget(Collection<?> sourceList, Class<T> target){
        if(sourceList == null){
            return null;
        }

        List<T> targetList = new ArrayList<>(sourceList.size());
        try {
            for(Object source : sourceList){
                T targetObject = target.newInstance();
                BeanUtils.copyProperties(source, targetObject);
                targetList.add(targetObject);
            }
        }catch (Exception e){
            log.error("convert error ", e);
        }

        return targetList;
    }
}
