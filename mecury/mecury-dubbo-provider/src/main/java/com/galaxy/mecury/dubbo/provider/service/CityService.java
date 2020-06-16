package com.galaxy.mecury.dubbo.provider.service;

import com.galaxy.mecury.dubbo.provider.entity.City;

/**
 * @Auther: peter
 * @Date: 2020/6/17 00:53
 * @Description:
 */
public interface CityService {
    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName
     */
    City findCityByName(String cityName);
}