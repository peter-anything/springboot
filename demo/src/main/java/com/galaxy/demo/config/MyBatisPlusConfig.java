package com.galaxy.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: peter
 * @Date: 2020/6/11 22:08
 * @Description:
 */
@Configuration
@MapperScan("com.galaxy.demo.dao")
public class MyBatisPlusConfig {
}
