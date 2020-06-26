package com.galaxy.mecury.java.deep.components.profile;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
public interface DatasourceConfig {
    public void setup();
}
