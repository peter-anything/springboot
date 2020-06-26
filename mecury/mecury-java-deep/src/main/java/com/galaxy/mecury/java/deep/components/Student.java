package com.galaxy.mecury.java.deep.components;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;

    @PostConstruct
    private void init() {
        System.out.println("init");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("destroy");
    }
}
