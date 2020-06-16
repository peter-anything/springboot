package com.galaxy.mecury.dubbo.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Auther: peter
 * @Date: 2020/6/17 00:44
 * @Description:
 */
@SpringBootApplication
@EnableDubboConfig
public class MecuryDubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MecuryDubboProviderApplication.class, args);
    }
}
