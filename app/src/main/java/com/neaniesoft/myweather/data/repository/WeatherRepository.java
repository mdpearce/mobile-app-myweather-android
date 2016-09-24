package com.neaniesoft.myweather.data.repository;

import android.support.annotation.NonNull;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class WeatherRepository implements WeatherProvider {

    private final WeatherProvider service;

    public WeatherRepository(@NonNull WeatherProvider service) {
        this.service = service;
    }

    @Override
    public void searchForCurrentWeather(@NonNull String searchString, @NonNull CurrentWeatherCallback callback) {

    }

    @Override
    public void searchForCurrentWeather(double lat, double lon, @NonNull CurrentWeatherCallback callback) {

    }
}
