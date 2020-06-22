package com.galaxy.mecury.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: peter
 * @Date: 2020/6/17 00:44
 * @Description:
 */

@SpringBootApplication
@EnableDubbo
public class MecuryDubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MecuryDubboProviderApplication.class, args);
    }
}
