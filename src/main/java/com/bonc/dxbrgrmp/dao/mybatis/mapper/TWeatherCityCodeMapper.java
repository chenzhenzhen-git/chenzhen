package com.bonc.dxbrgrmp.dao.mybatis.mapper;

import com.bonc.dxbrgrmp.pojo.po.TWeatherCityCode;

import java.util.List;

public interface TWeatherCityCodeMapper {

    //通过region_id查询  site_region_code, sity_url
    TWeatherCityCode  selectSiteRegionAndSityUrlCodeByRegionId(Integer regionId);

}