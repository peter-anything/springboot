package com.galaxy.venus.account.service.impl;

import com.galaxy.venus.account.service.AccountService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class AccountServiceImpl implements AccountService {
    @Override
    public String getUserName() {
        return "peter.wang";
    }
}
