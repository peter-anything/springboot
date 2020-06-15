package com.galaxy.mecury.api.service;

import com.galaxy.mecury.entity.Mail;

/**
 * @Auther: peter
 * @Date: 2020/6/14 23:40
 * @Description:
 */
public interface MailProducerService {
    public boolean send(Mail mail) throws Exception;
}
