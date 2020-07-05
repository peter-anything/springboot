package com.galaxy.mecury.java.deep.components;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomizedBeanName implements BeanClassLoaderAware {
    private String beanName = null;

    @Value("wangxiaobin")
    private String testName;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }
}
