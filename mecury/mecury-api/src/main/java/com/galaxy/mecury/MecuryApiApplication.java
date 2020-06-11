package com.galaxy.mecury;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.galaxy.mecury.dao")
@SpringBootApplication
public class MecuryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MecuryApiApplication.class, args);
    }

}
