package com.neaniesoft.myweather.data.repository;

import android.support.annotation.NonNull;

import com.neaniesoft.myweather.Injection;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class WeatherRepository implements WeatherProvider {

    private final WeatherProvider service;

    private static WeatherRepository sInstance;

    public WeatherRepository getInstance() {
        if (sInstance == null) {
            sInstance = new WeatherRepository(Injection.provideOpenWeatherMapProvider());
        }
        return sInstance;
    }

    private WeatherRepository(@NonNull WeatherProvider service) {
        this.service = service;
    }

    @Override
    public void searchForCurrentWeather(@NonNull String searchString, @NonNull CurrentWeatherCallback callback) {
        service.searchForCurrentWeather(searchString, callback);
    }

    @Override
    public void searchForCurrentWeather(double lat, double lon, @NonNull CurrentWeatherCallback callback) {
        service.searchForCurrentWeather(lat, lon, callback);
    }
}
