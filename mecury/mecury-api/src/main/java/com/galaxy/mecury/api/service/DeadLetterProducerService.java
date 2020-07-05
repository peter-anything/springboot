package com.galaxy.mecury.api.service;

import com.galaxy.mecury.api.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class DeadLetterProducerService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(int number) {
        log.warn("DeadLetterSender : {}", number);
        // 这里的Exchange可以是业务的Exchange，为了方便测试这里直接往死信Exchange里投递消息
        rabbitTemplate.convertAndSend(
                RabbitConfig.DEAD_LETTER_EXCHANGE,
                RabbitConfig.DEAD_LETTER_TEST_ROUTING_KEY,
                number
                );
    }
}
