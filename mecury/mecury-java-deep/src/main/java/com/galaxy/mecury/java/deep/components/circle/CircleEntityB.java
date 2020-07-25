package com.galaxy.mecury.java.deep.components.circle;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircleEntityB {
    @Autowired
    private CircleEntityA circleEntityA;

    public CircleEntityA getCircleEntityC() {
        return circleEntityA;
    }
}
