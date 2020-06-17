package com.galaxy.mecury.dubbo.service;

import com.galaxy.mecury.entity.City;

public interface CityService {
    public City findCityByName(String cityName);
}
