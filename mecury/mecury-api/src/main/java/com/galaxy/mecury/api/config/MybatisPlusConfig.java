package com.galaxy.mecury.api.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: peter
 * @Date: 2020/6/16 00:11
 * @Description:
 */
@Configuration
@MapperScan("com.galaxy.mecury.dao")
public class MybatisPlusConfig {
}
