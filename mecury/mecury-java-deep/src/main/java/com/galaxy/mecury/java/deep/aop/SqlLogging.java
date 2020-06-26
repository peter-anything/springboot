package com.galaxy.mecury.java.deep.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SqlLogging {
    @Pointcut("execution(* com.galaxy.mecury.java.deep.service.*.insert(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("log sql before execute really!");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("log sql after execute really!");
    }
}
