package com.galaxy.mecury.dubbo.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: peter
 * @Date: 2020/6/17 00:57
 * @Description:
 */
@EnableDubboConfig
@SpringBootApplication
public class MecuryDubboConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MecuryDubboConsumerApplication.class, args);
    }
}
