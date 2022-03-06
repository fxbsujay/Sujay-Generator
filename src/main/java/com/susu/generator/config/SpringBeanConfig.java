package com.susu.generator.config;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p> Spring Bean 工具类 </p>
 * @author fxbsujay@gmail.com
 * @date 22:35 2022/3/6
 * @version 1.0
 */
@Component
public class SpringBeanConfig implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanConfig.applicationContext == null) {
            SpringBeanConfig.applicationContext = applicationContext;
        }
    }

    public static Object getBean(Class clazz) {
        return SpringBeanConfig.applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) {
        return SpringBeanConfig.applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
        return SpringBeanConfig.applicationContext.getEnvironment().getProperty(key);
    }

}
