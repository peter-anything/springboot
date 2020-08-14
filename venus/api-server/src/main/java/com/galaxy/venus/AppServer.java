package com.galaxy.venus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

@SpringBootApplication
@MapperScan
public class AppServer {
    public static void main(String[] args) {
        SpringApplication.run(AppServer.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    }
}
