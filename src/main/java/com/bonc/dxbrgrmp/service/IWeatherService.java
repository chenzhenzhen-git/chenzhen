package com.bonc.dxbrgrmp.service;

import com.bonc.dxbrgrmp.pojo.po.CityWeatherInfo;
import com.bonc.dxbrgrmp.pojo.po.HourWeatherInfo;

import java.util.List;

public interface IWeatherService {



    public CityWeatherInfo get7DaysWeather(String cityId,String weatherurl);

    public CityWeatherInfo get15DaysWeather(String cityId,String weatherurl);

    public List<HourWeatherInfo> get24HourFuture(String cityId, String weatherurl);
}
