package com.bonc.dxbrgrmp.pojo.po;

public class DailyWeatherInfo {
    private String dayStr;
    private String wea;//晴、多云、
    private String tempL;//最低气温
    private String tempH;//最高气温
    private String win;//风力

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getTempL() {
        return tempL;
    }

    public void setTempL(String tempL) {
        this.tempL = tempL;
    }

    public String getTempH() {
        return tempH;
    }

    public void setTempH(String tempH) {
        this.tempH = tempH;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }
}
