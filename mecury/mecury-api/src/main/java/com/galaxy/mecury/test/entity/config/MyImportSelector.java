package com.galaxy.mecury.test.entity.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Cat.class.getName(), Dog.class.getName()};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }
}
