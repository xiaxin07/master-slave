package com.xiaxin.mapper;

import com.xiaxin.annotation.MasterDataSource;
import com.xiaxin.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CityMapper {

//    @Insert("INSERT into city (id,city_name) VALUES (#{id,jdbcType=INTEGER}, #{cityName,jdbcType=VARCHAR});")
    @MasterDataSource
    void insertCity(City city);

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    @Select("select id,city_name from city where city_name like CONCAT('%', #{cityName},'%')")
    List<City> selectByName(@Param("cityName") String cityName);
}