package com.smsapi.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	

	@Bean("dataSource")
    public DataSource getDataSource() {
   
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/db?useSSL=false");
        dataSourceBuilder.username("user");
        dataSourceBuilder.password("password");
        return dataSourceBuilder.build();
    }
}
