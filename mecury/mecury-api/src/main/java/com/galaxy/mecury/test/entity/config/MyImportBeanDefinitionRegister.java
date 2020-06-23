package com.galaxy.mecury.test.entity.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(Cat.class);
        registry.registerBeanDefinition(Cat.class.getName(), beanDefinition);
        RootBeanDefinition beanDefinition2 = new RootBeanDefinition(Dog.class);
        registry.registerBeanDefinition(Dog.class.getName(), beanDefinition2);
//        if (definition1 && definition2) {
//            RootBeanDefinition beanDefinition = new RootBeanDefinition()
//        }
    }
}
