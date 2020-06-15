package com.galaxy.mecury.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.galaxy.mecury.api.config.RabbitConfig;
import com.galaxy.mecury.api.service.MailProducerService;
import com.galaxy.mecury.api.service.MsgLogService;
import com.galaxy.mecury.api.util.JsonUtil;
import com.galaxy.mecury.entity.Mail;
import com.galaxy.mecury.entity.MsgLog;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: peter
 * @Date: 2020/6/14 23:41
 * @Description:
 */
@Service
public class MailProducerServiceImpl implements MailProducerService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MsgLogService msgLogService;

    @Override
    public boolean send(Mail mail) throws Exception {
        String msgId = UUID.randomUUID().toString().replaceAll("-", "");
        mail.setMsgId(msgId);

        // 入库，适合数据量写入比较少的情况，写入大的情况考虑 redis
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setMsg(mail.toString());
        msgLog.setExchange(RabbitConfig.MAIL_EXCHANGE_NAME);
        msgLog.setRoutingKey(RabbitConfig.MAIL_ROUTING_KEY_NAME);
        Date now = new Date();
        msgLog.setCreatedTime(now);
        msgLog.setUpdatedTime(now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.SECOND, 30);
        msgLog.setNextTryTime(calendar.getTime());
        msgLogService.save(msgLog);

        //发送消息到rabbitMQ
        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME, mail, correlationData);

        return true;
    }
}
