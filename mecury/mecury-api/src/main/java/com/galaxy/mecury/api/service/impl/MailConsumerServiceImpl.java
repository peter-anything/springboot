package com.galaxy.mecury.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.galaxy.mecury.api.config.RabbitConfig;
import com.galaxy.mecury.api.service.MailConsumerService;
import com.galaxy.mecury.api.service.MsgLogService;
import com.galaxy.mecury.api.util.JsonUtil;
import com.galaxy.mecury.api.util.SendMailUtil;
import com.galaxy.mecury.entity.Mail;
import com.galaxy.mecury.entity.MsgLog;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @Auther: peter
 * @Date: 2020/6/15 00:15
 * @Description:
 */
@Component
@Slf4j
public class MailConsumerServiceImpl implements MailConsumerService {
    @Resource
    private SendMailUtil sendMailUtil;

    @Resource
    private MsgLogService msgLogService;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    @Override
    public void consume(Message message, Channel channel) throws IOException {
        Mail mail = JSON.parseObject(new String(message.getBody()), Mail.class);
        log.info("收到消息: {}", mail.toString());

        QueryWrapper<MsgLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("msg_id", mail.getMsgId());
        MsgLog msgLog = msgLogService.getOne(queryWrapper);

        if (null == msgLog || msgLog.getStatus().equals(1)) {// 消费幂等性
            log.info("重复消费, msgId: {}", msgLog.getMsgId());
            return;
        }

        boolean success = sendMailUtil.send(mail);
        if (success) {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } else {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
