package com.galaxy.mecury.java.deep.components.factory;

import com.galaxy.mecury.java.deep.components.Student;
import com.galaxy.mecury.java.deep.components.condition.WindowsCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

public class StaticStudentFacotry {
    @Conditional({WindowsCondition.class})
    @Bean
    public static Student getStudent(@Value("测试用例")String name) {
        return new Student(name);
    }
}
