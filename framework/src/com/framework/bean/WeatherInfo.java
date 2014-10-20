package com.framework.bean;

public class WeatherInfo {
    // {"weatherinfo":{"city":"北京","cityid":"101010100","temp":"16","WD":"东风","WS":"2级","SD":"87%","WSE":"2",
    // "time":"14:25","isRadar":"1","Radar":"JC_RADAR_AZ9010_JB","njd":"暂无实况","qy":"1013"}}

    private String city;
    private String cityid;
    private String temp;
    private String WD;
    private String WS;
    private String SD;
    private String WSE;
    private String time;
    private String isRadar;
    private String Radar;
    private String njd;
    private String qy;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String wD) {
        WD = wD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String wS) {
        WS = wS;
    }

    public String getSD() {
        return SD;
    }

    public void setSD(String sD) {
        SD = sD;
    }

    public String getWSE() {
        return WSE;
    }

    public void setWSE(String wSE) {
        WSE = wSE;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsRadar() {
        return isRadar;
    }

    public void setIsRadar(String isRadar) {
        this.isRadar = isRadar;
    }

    public String getRadar() {
        return Radar;
    }

    public void setRadar(String radar) {
        Radar = radar;
    }

    public String getNjd() {
        return njd;
    }

    public void setNjd(String njd) {
        this.njd = njd;
    }

    public String getQy() {
        return qy;
    }

    @Override
    public String toString() {
        return "WeatherInfo [city=" + city + ", cityid=" + cityid 
                + ", temp=" + temp + ", WD=" + WD 
                + ", WS=" + WS + ", SD=" + SD 
                + ", WSE=" + WSE + ", time=" + time 
                + ", isRadar=" + isRadar + ", Radar=" + Radar 
                + ", njd=" + njd + ", qy=" + qy + "]"
                + super.toString();
    }

    public void setQy(String qy) {
        this.qy = qy;
    }
}