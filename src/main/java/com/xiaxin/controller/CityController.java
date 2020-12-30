package com.xiaxin.controller;

import com.xiaxin.entity.City;
import com.xiaxin.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/add")
    public String addCity(String cityName) {
        cityService.addCity(new City().setCityName(cityName));
        return "success";
    }
    @GetMapping("/getCityByCityName")
    public List<City> getCityByCityName(String cityName) {
        return cityService.getCityByCityName(cityName);
    }
}
