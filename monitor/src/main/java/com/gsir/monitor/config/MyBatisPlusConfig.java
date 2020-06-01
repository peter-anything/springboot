package com.gsir.monitor.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.gsir.monitor.mapper")
public class MyBatisPlusConfig {
}
