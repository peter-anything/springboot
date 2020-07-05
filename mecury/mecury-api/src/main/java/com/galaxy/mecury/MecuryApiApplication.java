package com.galaxy.mecury;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "com.galaxy.mecury.api.config",
        "com.galaxy.mecury.api.controller",
        "com.galaxy.mecury.api.service",
        "com.galaxy.mecury.service",
        "com.galaxy.mecury.api.util"})
public class MecuryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MecuryApiApplication.class, args);
    }

}
