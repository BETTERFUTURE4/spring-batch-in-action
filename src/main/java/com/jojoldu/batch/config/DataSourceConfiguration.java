package com.jojoldu.batch.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by jojoldu@gmail.com on 22/05/2020
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties
public class DataSourceConfiguration {
    private static final String PREFIX = "spring.datasource.hikari";
    public static final String MAIN_DATASOURCE = "dataSource";
    public static final String OTHER_DATASOURCE = "otherDataSource";

    @Bean(MAIN_DATASOURCE)
    @Primary
    @ConfigurationProperties(prefix = PREFIX)
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @SuppressWarnings("ConfigurationProperties")
    @Bean(OTHER_DATASOURCE)
    @ConfigurationProperties(prefix = PREFIX)
    public DataSource otherDataSource() {
        HikariDataSource hikariDataSource = DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
        hikariDataSource.setReadOnly(true);
        return hikariDataSource;
    }


}
