package com.galaxy.venus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OperationDemo {
    public static int cal() {
        int a = 10 + 5;
        int b = a + 3;
        b = b + 6;
        b = b + 128;
        b = b + 32768;
        short a_s = 5 + 10;
        short b_s = (short)(a_s + 3);
        float a_f = 5.00F + 10.00F;
        float b_f = a_f + 3.00F;
        double a_d = 5.00D +10.00D;
        double b_d = a_d + 3.00D;
        String a_str = "a" + "b";
        String b_str = a_str + "c";
        return b;
    }

    public static void main(String[] args) {
        String str = "DGID_1231231";

        if (str.startsWith("DGID_")) {
            Long num = Long.parseLong(str.substring(5));
            System.out.println(num);
        }
    }
}
