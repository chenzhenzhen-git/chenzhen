package com.bonc.dxbrgrmp.controller;

import com.bonc.dxbrgrmp.pojo.po.TWeatherCityCode;
import com.bonc.dxbrgrmp.response.ResultModel;
import com.bonc.dxbrgrmp.service.IWeatherService;
import com.bonc.dxbrgrmp.service.TWeatherCityCodeService;
import com.bonc.dxbrgrmp.service.serviceimpl.TWeatherCityCodeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class WeatherController {

    @Resource
    IWeatherService weatherServiceImpl;

    @Resource
    TWeatherCityCodeService tWeatherCityCodeServiceImpl;


    @RequestMapping("/get7DaysWeatherInfo/{regionId}")
    @ResponseBody
    public ResultModel getWeatherInfo(@PathVariable Integer regionId){
        TWeatherCityCode tWeatherCityCode= tWeatherCityCodeServiceImpl.selectSiteRegionAndSityUrlCodeByRegionId(regionId);
        String weatherurl=tWeatherCityCode.getSityUrl();
        System.out.println("天气url"+weatherurl);
        String cityId=tWeatherCityCode.getSiteRegionCode();
        System.out.println("城市ID"+cityId);
        if(cityId == null || cityId.equals("")){
            return ResultModel.failModel("城市编码不能为空 ");
        }
        return ResultModel.successModel(weatherServiceImpl.get7DaysWeather(cityId,weatherurl));
    }

    @RequestMapping("/get15DaysWeatherInfo/{regionId}")
    @ResponseBody
    public ResultModel get15DaysWeather(@PathVariable Integer regionId){
        TWeatherCityCode tWeatherCityCode= tWeatherCityCodeServiceImpl.selectSiteRegionAndSityUrlCodeByRegionId(regionId);
        String weatherurl=tWeatherCityCode.getSityUrl();
        String cityId=tWeatherCityCode.getSiteRegionCode();
        if(cityId == null || cityId.equals("")){
            return ResultModel.failModel("城市编码不能为空");
        }
        return ResultModel.successModel(weatherServiceImpl.get15DaysWeather(cityId,weatherurl));
    }

    @RequestMapping(value = "/get24HourFuture/{regionId}")
    @ResponseBody
    public ResultModel get24HourFuture(@PathVariable Integer regionId){
        TWeatherCityCode tWeatherCityCode= tWeatherCityCodeServiceImpl.selectSiteRegionAndSityUrlCodeByRegionId(regionId);
        String weatherurl=tWeatherCityCode.getSityUrl();
        String cityId=tWeatherCityCode.getSiteRegionCode();
        if(cityId == null || cityId.equals("")){
            return ResultModel.failModel("城市编码不能为空");
        }
        return ResultModel.successModel(weatherServiceImpl.get24HourFuture(cityId,weatherurl));
    }
}
