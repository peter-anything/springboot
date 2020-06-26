package com.galaxy.mecury.java.deep.components;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Data
public class SimpleBean implements InitializingBean, DisposableBean {
    private  String name;
    private String sex;
    private String age;

    @Override
    public void destroy() throws Exception {
        System.out.println("this is info from afterpropertiesSet from SimpleBean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("this is info from customer destroy method");
    }
}
