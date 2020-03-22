package com.example.sorteador.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource(){

        DataSourceBuilder dsb = DataSourceBuilder.create();
        dsb.driverClassName(env.getProperty("dbdriver"));
        dsb.password(env.getProperty("password"));
        dsb.username(env.getProperty("user"));
        dsb.url(env.getProperty("url"));

        return dsb.build();
    }
}