package com.galaxy.mecury.java.deep.annotation;

import javafx.scene.control.Tab;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Table(name = "王小彬", age = 25, gender = true)
public class Test {
    @Table(name = "王小彬", age = 26, gender = true)
    private String user;

    @Table(name = "王小彬", age = 27, gender = true)
    private void sayHello() {

    }

    public static void main(String[] args) throws ClassNotFoundException {
        parseTypeAnnotation();
        parseFieldAnnotation();
        parseMethodAnnotation();
    }

    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("com.galaxy.mecury.java.deep.annotation.Test");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }

    public static void parseMethodAnnotation() throws ClassNotFoundException {
        for (Method method : Test.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Table.class)) {
                Annotation annotation = method.getAnnotation(Table.class);
                System.out.println(annotation);
            }
        }
    }

    public static void parseFieldAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("com.galaxy.mecury.java.deep.annotation.Test");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Table.class)) {
                Annotation annotation = field.getAnnotation(Table.class);
                System.out.println(annotation);
            }
        }
    }
}
