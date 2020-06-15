package com.galaxy.mecury.api.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @Auther: peter
 * @Date: 2020/6/15 00:13
 * @Description:
 */
public interface MailConsumerService {
    public void consume(Message message, Channel channel) throws IOException;
}
