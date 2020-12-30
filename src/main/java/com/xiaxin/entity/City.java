package com.xiaxin.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class City {
 
    /**
     * 城市编号
     */
    private Long id;
 
    /**
     * 省份编号
     */
    private String cityName;

}