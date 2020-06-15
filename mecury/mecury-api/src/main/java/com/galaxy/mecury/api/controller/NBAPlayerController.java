package com.galaxy.mecury.api.controller;

import com.galaxy.mecury.entity.NBAPlayer;
import com.galaxy.mecury.api.service.NBAPlayerService;
import com.galaxy.mecury.api.service.RedisService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: peter
 * @Date: 2020/6/11 00:37
 * @Description:
 */
@RestController
@RequestMapping("/nbaplayer")
public class NBAPlayerController {
    @Resource
    private NBAPlayerService nbaPlayerService;

    @Resource
    private RedisService redisService;

    @RequestMapping("/list")
    public List<NBAPlayer> list() {
        return nbaPlayerService.getAll();
    }

    class ThreadDecrementTotalDemo implements Runnable {

        @Override
        public void run() {
            String key = "sku_total";
            try {
                Thread.sleep(2000);
            }catch (Exception e) {
                e.printStackTrace();
            }

            if (redisService.decrementAtomic(key)) {
                System.out.println("购买成功");
            } else {
                System.out.println("购买失败");
            }
        }
    }

    @RequestMapping("/set/skutotal")
    public String setSkuTotal() {
        redisService.set("sku_total", 10);
        return "10";
    }

    @RequestMapping("/seckill")
    public String secKill() throws InterruptedException {
        Thread[] threads = new Thread[40];
        for(int i = 0; i < 40; i ++) {
            Thread t = new Thread(new ThreadDecrementTotalDemo());
            threads[i] = t;
        }

        for ( Thread t : threads) {
            t.start();
            t.join();
        }
        return "Finished";
    }

}