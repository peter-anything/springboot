package com.galaxy.mecury.dubbo.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: peter
 * @Date: 2020/6/17 00:57
 * @Description:
 */
@SpringBootApplication
public class MecuryDubboConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MecuryDubboConsumerApplication.class, args);
    }
}
