package com.galaxy.mecury.dubbo.provider.service.impl;

import com.galaxy.mecury.dubbo.service.CityService;
import com.galaxy.mecury.entity.City;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Auther: peter
 * @Date: 2020/6/17 00:55
 * @Description:
 */
@Service(interfaceClass = CityService.class)
@Component
public class CityServiceImpl implements CityService {

    public City findCityByName(String cityName) {
        System.out.println("request cityName: " + cityName);
        return new City("武汉", "湖北");
    }
}
