package com.buzz.app.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceDataSourceConfig {
	
	@Bean  
	@ConfigurationProperties(prefix="spring.interface.datasource")
    public DataSource interfaceDataSource() {
        return DataSourceBuilder.create().build();
    }
}
