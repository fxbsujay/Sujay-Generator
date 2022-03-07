package com.susu.generator.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> 动态数据源配置 </p>
 * @author fxbsujay@gmail.com
 * @date 22:35 2022/3/6
 * @version 1.0
 */
public class DynamicDataSourceConfig extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> DATA_SOURCE_KEY = ThreadLocal.withInitial(() -> "defaultDataSource");

    public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>(10);

    static {
        dataSourcesMap.put("defaultDataSource", SpringBeanConfig.getBean("defaultDataSource"));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println(DynamicDataSourceConfig.DATA_SOURCE_KEY.get());
        return DynamicDataSourceConfig.DATA_SOURCE_KEY.get();
    }

    /**
     * 新增数据源
     * @param dataSource 数据源
     */
    public static void setDataSource(String dataSource) {
        DynamicDataSourceConfig.DATA_SOURCE_KEY.set(dataSource);
        DynamicDataSourceConfig dynamicDataSource = (DynamicDataSourceConfig) SpringBeanConfig.getBean("dataSource");
        dynamicDataSource.afterPropertiesSet();
    }

    /**
     * 获取数据源
     */
    public static String getDataSource() {
        return DynamicDataSourceConfig.DATA_SOURCE_KEY.get();
    }

    /**
     * 删除数据源
     */
    public static void clear() {
        DynamicDataSourceConfig.DATA_SOURCE_KEY.remove();
    }
}
