package com.galaxy.mecury.java.deep.components.factory;

import com.galaxy.mecury.java.deep.components.SimpleBean;
import com.galaxy.mecury.java.deep.components.Student;
import com.galaxy.mecury.java.deep.components.condition.WindowsCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

public class StaticSimpleBeanFactory {
    @Conditional({WindowsCondition.class})
    @Bean
    public static SimpleBean getStudent(@Value("测试用例") String name) {
        SimpleBean simpleBean = new SimpleBean();
        simpleBean.setName("Test simple bean");
        return simpleBean;
    }
}
