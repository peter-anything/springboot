package com.gsir.monitor.service;

import com.gsir.monitor.service.impl.TestUserServiceImpl;
import com.gsir.monitor.service.proxy.TestUserServiceInterceptor;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

public class TestUserServiceTest {
    @Test
    public void dynamicProxy() {
        TestUserServiceImpl testUserService = new TestUserServiceImpl();
        TestUserServiceInterceptor proxy = new TestUserServiceInterceptor();
        TestUserService testUser = (TestUserService) proxy.getProxy(testUserService.getClass());
        testUser.select();
    }
}
