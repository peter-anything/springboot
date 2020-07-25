package com.galaxy.mecury.api;

import com.alibaba.fastjson.JSON;
import com.galaxy.mecury.api.service.MailConsumerService;
import com.galaxy.mecury.api.service.MailProducerService;
import com.galaxy.mecury.api.service.RedisService;
import com.galaxy.mecury.entity.Mail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MecuryApiApplicationTests {
    @Resource
    private RedisService redisService;

    @Resource
    private MailConsumerService mailConsumerService;

    @Resource
    private MailProducerService mailProducerService;

    @Test
    void testRedisSet() throws InterruptedException {
        redisService.set("sku_total", 10);
    }

    class ThreadDemo implements Runnable {

        @Override
        public void run() {
            String key = "lock_test";
            if (redisService.lock(key, 60)) {
                System.out.println("get key from redis");
                redisService.unlock(key);
                System.out.println("release key from redis");
            } else {
                System.out.println("get key from redis failed");
            }
        }
    }

    @Test
    void testRedisDistributedLock() throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new ThreadDemo());
            threads[i] = t;
        }

        for (Thread t : threads) {
            t.start();
            t.join();
        }
    }

    class ThreadDecrementTotalDemo implements Runnable {

        @Override
        public void run() {
            String key = "sku_total";

            if (redisService.decrementAtomic(key)) {
                System.out.println("购买成功");
            }
        }
    }

    @Test
    void testAtomicDecrement() throws InterruptedException {
        String key = "sku_total";
        Thread[] threads = new Thread[40];
        for (int i = 0; i < 40; i++) {
            Thread t = new Thread(new ThreadDecrementTotalDemo());
            threads[i] = t;
        }

        for (Thread t : threads) {
            t.start();
            t.join();
        }
    }

    @Test
    void testSendMail() throws Exception {
//        Mail mail = new Mail();
//        mail.setTo("15201856760@163.com");
//        mail.setTitle("Hello");
//        mail.setContent("Hello, world!");
//        mailProducerService.send(mail);
    }

    @Test
    void testSerialize() throws Exception {
        String test = "{\"content\":\"Hello, world!\",\"msgId\":\"abe3bd25049346fb8885da9065f2a462\",\"title\":\"Hello\",\"to\":\"15201856760@163.com\"}";
        Mail mail = JSON.parseObject(test, Mail.class);
        System.out.println(mail);
    }
}
