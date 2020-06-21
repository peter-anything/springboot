package com.galaxy.mecury.dubbo;

import java.io.Serializable;

public class Hello implements Serializable {

    private static final long serialVersionUID = 753429849957096150L;

    private String name;

    public Hello() {
    }

    public Hello(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}