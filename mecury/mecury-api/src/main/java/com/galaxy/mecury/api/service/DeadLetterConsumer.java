package com.galaxy.mecury.api.service;

import com.galaxy.mecury.api.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@RabbitListener(queues = RabbitConfig.DEAD_LETTER_QUEUE, concurrency = "10")
@Slf4j
@Component
public class DeadLetterConsumer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public void testDeadLetterQueueAndThrowsException(@Payload Integer number) {
        log.warn("DeadLetterConsumer :{}/0 ", number);
    }
}
