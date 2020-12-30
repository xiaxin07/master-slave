package com.xiaxin.service;

import com.xiaxin.entity.City;

import java.util.List;

public interface CityService {
    void addCity(City city);

    List<City> getCityByCityName(String cityName);
}
