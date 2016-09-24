package com.neaniesoft.myweather.data.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.neaniesoft.myweather.data.model.CurrentWeather;

/**
 * Created by mdpearce on 24/09/2016.
 */

public interface WeatherProvider {

    void searchForCurrentWeather(@NonNull String searchString, @NonNull CurrentWeatherCallback callback);

    void searchForCurrentWeather(double lat, double lon, @NonNull CurrentWeatherCallback callback);

    interface CurrentWeatherCallback {
        void onCurrentWeatherReceived(@NonNull CurrentWeather currentWeather);
        void onNoCurrentWeatherAvailable(@Nullable String error);
    }
}
