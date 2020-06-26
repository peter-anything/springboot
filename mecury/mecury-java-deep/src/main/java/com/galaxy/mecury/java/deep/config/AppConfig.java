package com.galaxy.mecury.java.deep.config;

import com.galaxy.mecury.entity.config.DBConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan("com.galaxy.mecury.java.deep")
public class AppConfig {
    @Bean
    public DBConfig dbConfig() {
        return new DBConfig();
    }
}
