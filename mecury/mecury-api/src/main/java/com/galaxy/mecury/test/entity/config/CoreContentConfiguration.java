package com.galaxy.mecury.test.entity.config;

import org.springframework.context.annotation.Bean;

class CoreContentService implements ContentService {
    @Override
    public void doSomething() {
        System.out.println("core");
    }
}

public class CoreContentConfiguration {
    @Bean
    public ContentService contentService() {
        return new CoreContentService();
    }
}
