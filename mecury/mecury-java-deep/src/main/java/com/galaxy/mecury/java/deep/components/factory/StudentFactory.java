package com.galaxy.mecury.java.deep.components.factory;

import com.galaxy.mecury.java.deep.components.Student;
import com.galaxy.mecury.java.deep.components.condition.LinuxCondition;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional({LinuxCondition.class})
public class StudentFactory implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        Student stu = new Student("wantgxiaobin");
        return stu;
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }
}
