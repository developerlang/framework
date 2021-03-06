package com.framework.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table WEATHER.
 */
public class Weather {

    private Long id;
    /** Not-null value. */
    private String cityid;
    private String city;
    private String temp;

    public Weather() {
    }

    public Weather(Long id) {
        this.id = id;
    }

    public Weather(Long id, String cityid, String city, String temp) {
        this.id = id;
        this.cityid = cityid;
        this.city = city;
        this.temp = temp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getCityid() {
        return cityid;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

}
