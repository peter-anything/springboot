package com.galaxy.mecury.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.galaxy.mecury.dubbo.provider.service.CityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: peter
 * @Date: 2020/6/17 00:59
 * @Description:
 */
@RestController
public class HelloController {

    @Reference(version = "${service.version}", check = false)
    private CityService cityService;

    @RequestMapping("/hello")
    public Object hello() {
        return cityService.findCityByName("武汉");
    }
}