package com.gsir.monitor;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.gsir.monitor.mapper")
public class MonitorApplication extends SpringBootServletInitializer {

    private static Logger logger = LoggerFactory.getLogger(MonitorApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(MonitorApplication.class, args);
        logger.info("Application Start Success!");
    }
}
