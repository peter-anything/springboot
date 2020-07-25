package com.galaxy.mecury.api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic topicInfo() {
        return new NewTopic("test", 3, (short) 2);
    }
}
