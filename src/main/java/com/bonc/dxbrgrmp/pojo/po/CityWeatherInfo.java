package com.bonc.dxbrgrmp.pojo.po;

import java.util.ArrayList;
import java.util.List;

public class CityWeatherInfo {
    private String cityId;
    private String cityName;
    private List<DailyWeatherInfo> dailyweatherList = new ArrayList<>();

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<DailyWeatherInfo> getDailyweatherList() {
        return dailyweatherList;
    }

    public void setDailyweatherList(List<DailyWeatherInfo> dailyweatherList) {
        this.dailyweatherList = dailyweatherList;
    }


//    pu
}
