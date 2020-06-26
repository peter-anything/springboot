package com.galaxy.mecury.java.deep.components;

import com.galaxy.mecury.entity.config.DBConfig;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {
    public DBConfig dbConfig() {
        return new DBConfig();
    }
}
