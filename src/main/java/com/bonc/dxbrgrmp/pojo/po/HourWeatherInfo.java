package com.bonc.dxbrgrmp.pojo.po;

import lombok.Data;

/**
 * @Auther: lgf
 * @Date: 2019/12/31
 * @Description: com.bonc.dxbrgrmp.pojo.po
 * @version: 1.0
 */
@Data
public class HourWeatherInfo {
    private String hour; //时间
    private String wea;//晴、多云、
    private String temp;//温度
    private String winA;//风向
    private String winB;//风力

    public HourWeatherInfo() {
        super();
    }
    public HourWeatherInfo(String hour, String wea, String temp, String winA, String winB) {
        super();
        this.hour = hour;
        this.wea = wea;
        this.temp = temp;
        this.winA = winA;
        this.winB = winB;
    }
}
