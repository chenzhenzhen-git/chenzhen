package com.bonc.dxbrgrmp.service;

import com.bonc.dxbrgrmp.pojo.po.TWeatherCityCode;

import java.util.List;


public interface TWeatherCityCodeService{

    //通过region_id查询site_region_code,sity_url
    TWeatherCityCode selectSiteRegionAndSityUrlCodeByRegionId(Integer regionId);
    //abc

}
