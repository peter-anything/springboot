package com.galaxy.mecury.entity.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class DBConfig {
    @Value("localhost")
    private String host;
    private String port;
    private String user;
    private String password;
}
