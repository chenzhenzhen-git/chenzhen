package com.bonc.dxbrgrmp.service.serviceimpl;

import com.bonc.dxbrgrmp.service.TWeatherCityCodeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.bonc.dxbrgrmp.dao.mybatis.mapper.TWeatherCityCodeMapper;
import com.bonc.dxbrgrmp.pojo.po.TWeatherCityCode;

import java.util.List;

@Service
public class TWeatherCityCodeServiceImpl implements TWeatherCityCodeService {


    @Resource
    private TWeatherCityCodeMapper tWeatherCityCodeMapper;

    //通过region_id查询site_region_code,sity_url
    @Override
    public TWeatherCityCode selectSiteRegionAndSityUrlCodeByRegionId(Integer regionId) {
        return tWeatherCityCodeMapper.selectSiteRegionAndSityUrlCodeByRegionId(regionId);
    }

}
