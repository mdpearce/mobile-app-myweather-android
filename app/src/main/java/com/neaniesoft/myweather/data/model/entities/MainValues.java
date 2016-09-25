package com.neaniesoft.myweather.data.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class MainValues {
    Double temp;
    Double pressure;
    Integer humidity;
    Double tempMin;
    Double tempMax;

    @SerializedName("sea_level")
    Double pressureSeaLevel;

    @SerializedName("grnd_level")
    Double pressureGroundLevel;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getPressureSeaLevel() {
        return pressureSeaLevel;
    }

    public void setPressureSeaLevel(Double pressureSeaLevel) {
        this.pressureSeaLevel = pressureSeaLevel;
    }

    public Double getPressureGroundLevel() {
        return pressureGroundLevel;
    }

    public void setPressureGroundLevel(Double pressureGroundLevel) {
        this.pressureGroundLevel = pressureGroundLevel;
    }
}
