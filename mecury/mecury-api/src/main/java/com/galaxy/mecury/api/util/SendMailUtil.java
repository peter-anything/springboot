package com.galaxy.mecury.api.util;

import com.galaxy.mecury.entity.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Auther: peter
 * @Date: 2020/6/14 23:38
 * @Description:
 */

@Component
@Slf4j
public class SendMailUtil {

    @Value("${spring.mail.from}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    /**
     * 发送简单邮件
     *
     * @param mail
     */
    public boolean send(Mail mail) {
        String to = mail.getTo();// 目标邮箱
        String title = mail.getTitle();// 邮件标题
        String content = mail.getContent();// 邮件正文

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("邮件发送成功");
            return true;
        } catch (MailException e) {
            log.error("邮件发送失败, to: {}, title: {}", to, title, e);
            return false;
        }
    }
}