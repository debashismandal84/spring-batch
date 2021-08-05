package com.example.batch.springbatch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class BatchDatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "datasource.batch.datasource")
    public DataSource getBatchDatabase(){

        return DataSourceBuilder.create().build();


    }

}
