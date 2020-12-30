package com.xiaxin.service.impl;

import com.xiaxin.entity.City;
import com.xiaxin.mapper.CityMapper;
import com.xiaxin.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper cityMapper;
    @Override
    public void addCity(City city) {
       cityMapper.insertCity(city);
    }

    @Override
    public List<City> getCityByCityName(String cityName) {


        return cityMapper.selectByName(cityName);
    }
}
