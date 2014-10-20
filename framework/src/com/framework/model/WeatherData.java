package com.framework.model;

import com.framework.bean.WeatherInfo;

public class WeatherData {

    private WeatherInfo weatherinfo;

    public WeatherInfo getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(WeatherInfo weatherinfo) {
        this.weatherinfo = weatherinfo;
    }
    
    @Override
    public String toString() {
        return weatherinfo == null ? "" : weatherinfo.toString();
    }
}
