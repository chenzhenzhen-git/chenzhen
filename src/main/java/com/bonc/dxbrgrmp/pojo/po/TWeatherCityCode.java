package com.bonc.dxbrgrmp.pojo.po;

import java.util.List;

public class TWeatherCityCode {

    List<String> RegionAndUrlList;

    public void setRegionAndUrlList(List<String> regionAndUrlList) {
        RegionAndUrlList = regionAndUrlList;
    }

    public List<String> getRegionAndUrlList() {
        return RegionAndUrlList;
    }

    /**
    * ID
    */
    private Integer tId;

    /**
    * 网站名称
    */
    private String sityName;

    /**
    * 网站网址
    */
    private String sityUrl;

    /**
    * 区域ID
    */
    private Integer regionId;

    /**
    * 网站区域编码
    */
    private String siteRegionCode;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String getSityName() {
        return sityName;
    }

    public void setSityName(String sityName) {
        this.sityName = sityName;
    }

    public String getSityUrl() {
        return sityUrl;
    }

    public void setSityUrl(String sityUrl) {
        this.sityUrl = sityUrl;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getSiteRegionCode() {
        return siteRegionCode;
    }

    public void setSiteRegionCode(String siteRegionCode) {
        this.siteRegionCode = siteRegionCode;
    }
}