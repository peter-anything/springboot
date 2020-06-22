package com.galaxy.mecury.test.entity.config;

import org.springframework.context.annotation.Bean;

class SimpleContentService implements ContentService {
    @Override
    public void doSomething() {
        System.out.println("simple");
    }
}

public class SimpleContentConfiguration {
    @Bean
    public ContentService contentService() {
        return new SimpleContentService();
    }
}