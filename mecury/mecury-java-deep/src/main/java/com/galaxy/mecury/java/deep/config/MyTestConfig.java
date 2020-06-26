package com.galaxy.mecury.java.deep.config;

import com.galaxy.mecury.java.deep.components.Car;
import com.galaxy.mecury.java.deep.components.Driver;
import com.galaxy.mecury.java.deep.components.factory.StaticSimpleBeanFactory;
import com.galaxy.mecury.java.deep.components.factory.StaticStudentFacotry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import({StaticStudentFacotry.class, StaticSimpleBeanFactory.class})
public class MyTestConfig {
    @Bean
    public Driver driver(Car car){
        Driver driver = new Driver();
        driver.setId(1);
        driver.setName("driver");
        driver.setCar(car);
        return driver;
    }

    @Bean
    public Car car(){
        Car car = new Car();
        car.setId(1);
        car.setName("car");
        return car;
    }
}
