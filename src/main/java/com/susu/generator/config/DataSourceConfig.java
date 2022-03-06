package com.susu.generator.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;



import javax.sql.DataSource;

/**
 * <p>Description: 数据源创建</p>
 * @author fxbsujay@gmail.com
 * @date 22:32 2022/3/6
 * @version 1.0
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource defaultDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    @DependsOn({"springBeanConfig", "defaultDataSource"})
    public DynamicDataSourceConfig dataSource() {
        DynamicDataSourceConfig dynamicDataSource = new DynamicDataSourceConfig();
        dynamicDataSource.setTargetDataSources(DynamicDataSourceConfig.dataSourcesMap);
        return dynamicDataSource;
    }
}
