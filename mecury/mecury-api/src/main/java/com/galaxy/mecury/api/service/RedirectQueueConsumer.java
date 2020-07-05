package com.galaxy.mecury.api.service;

import com.galaxy.mecury.api.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = RabbitConfig.REDIRECT_QUEUE)
@Component
@Slf4j
public class RedirectQueueConsumer {
    /**
     * 重定向队列和死信队列形参一致Integer number
     * @param number
     */
    @RabbitHandler
    public void fromDeadLetter(Integer number){
        log.warn("RedirectQueueConsumer : {}", number);
        // 对应的操作
        int i = number / 1;
    }
}
