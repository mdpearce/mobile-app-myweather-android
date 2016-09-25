package com.neaniesoft.myweather.data.model;

import com.google.gson.annotations.SerializedName;
import com.neaniesoft.myweather.data.model.entities.Clouds;
import com.neaniesoft.myweather.data.model.entities.CoOrd;
import com.neaniesoft.myweather.data.model.entities.MainValues;
import com.neaniesoft.myweather.data.model.entities.Rain;
import com.neaniesoft.myweather.data.model.entities.Snow;
import com.neaniesoft.myweather.data.model.entities.Sys;
import com.neaniesoft.myweather.data.model.entities.WeatherConditions;
import com.neaniesoft.myweather.data.model.entities.Wind;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class CurrentWeather {
    @SerializedName("coord")
    CoOrd LocationCoOrds;

    @SerializedName("weather")
    WeatherConditions[] weatherConditions;

    @SerializedName("main")
    MainValues mainValues;

    Wind wind;

    Clouds clouds;

    Rain rain;

    Snow snow;

    @SerializedName("dt")
    Long timeOfData;

    Sys sys;

    @SerializedName("id")
    Long cityId;

    @SerializedName("name")
    String cityName;

    public CoOrd getLocationCoOrds() {
        return LocationCoOrds;
    }

    public void setLocationCoOrds(CoOrd locationCoOrds) {
        LocationCoOrds = locationCoOrds;
    }

    public WeatherConditions[] getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(WeatherConditions[] weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public MainValues getMainValues() {
        return mainValues;
    }

    public void setMainValues(MainValues mainValues) {
        this.mainValues = mainValues;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Long getTimeOfData() {
        return timeOfData;
    }

    public void setTimeOfData(Long timeOfData) {
        this.timeOfData = timeOfData;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public WeatherConditions getPrimaryWeatherConditions() {
        if (weatherConditions != null && weatherConditions.length > 0) {
            return weatherConditions[0];
        }
        return null;
    }

    public Long getSunrise() {
        return sys != null ? sys.getSunrise() : null;
    }

    public Long getSunset() {
        return sys != null ? sys.getSunset() : null;
    }
}
