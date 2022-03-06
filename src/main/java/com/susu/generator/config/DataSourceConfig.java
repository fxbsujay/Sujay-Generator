package com.susu.generator.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Author sujay
 * @Description 描述
 * @Date 22:32 2022/3/6
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
    @DependsOn({"springUtils", "defaultDataSource"})
    public DynamicDataSourceConfig dataSource() {
        DynamicDataSourceConfig dynamicDataSource = new DynamicDataSourceConfig();
        dynamicDataSource.setTargetDataSources(DynamicDataSourceConfig.dataSourcesMap);
        return dynamicDataSource;
    }
}
