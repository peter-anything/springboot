package com.galaxy.venus.account.controller;

import com.galaxy.venus.account.service.AccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    // 使用兼容注入，可以使用dubbo原生注解@Reference注入
    @DubboReference
    public AccountService service;

    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable String name){
        return service.getUserName();
    }
}
