package com.galaxy.mecury.api.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaHelloConsumer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = {"hello"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.error("consume kafka message!");
        System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
    }
}
