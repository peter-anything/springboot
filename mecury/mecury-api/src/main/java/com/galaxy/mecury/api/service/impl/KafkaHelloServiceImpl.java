package com.galaxy.mecury.api.service.impl;

import com.galaxy.mecury.api.service.KafkaHelloService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Delayed;

@Service
public class KafkaHelloServiceImpl implements KafkaHelloService {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void sendHello(String key, String data) {
        String topic = "hello";
        kafkaTemplate.send(new ProducerRecord(topic, key, data));
    }
}
