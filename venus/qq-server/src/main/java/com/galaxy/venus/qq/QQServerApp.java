package com.galaxy.venus.qq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.galaxy.venus.qq.server"})
public class QQServerApp {
    public static void main(String[] args) {
        SpringApplication.run(QQServerApp.class);
    }
}
