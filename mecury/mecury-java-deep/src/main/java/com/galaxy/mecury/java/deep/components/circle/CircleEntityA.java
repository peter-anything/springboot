package com.galaxy.mecury.java.deep.components.circle;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircleEntityA {
    @Autowired
    private CircleEntityB circleEntityB;

    public CircleEntityB getCircleEntityB() {
        return circleEntityB;
    }
}
