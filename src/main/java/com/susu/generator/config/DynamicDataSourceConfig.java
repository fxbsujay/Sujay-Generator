package com.susu.generator.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author sujay
 * @Description 描述
 * @Date 22:35 2022/3/6
 */
public class DynamicDataSourceConfig extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> DATA_SOURCE_KEY = ThreadLocal.withInitial(() -> "defaultDataSource");

    public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>(10);

    static {
        dataSourcesMap.put("defaultDataSource", SpringUtils.getBean("defaultDataSource"));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceConfig.DATA_SOURCE_KEY.get();
    }

    public static void setDataSource(String dataSource) {
        DynamicDataSourceConfig.DATA_SOURCE_KEY.set(dataSource);
        DynamicDataSourceConfig dynamicDataSource = (DynamicDataSourceConfig) SpringUtils.getBean("dataSource");
        dynamicDataSource.afterPropertiesSet();
    }

    public static String getDataSource() {
        return (String) DynamicDataSourceConfig.DATA_SOURCE_KEY.get();
    }

    public static void clear() {
        DynamicDataSourceConfig.DATA_SOURCE_KEY.remove();
    }
}
