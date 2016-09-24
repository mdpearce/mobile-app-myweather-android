package com.neaniesoft.myweather.data.repository.remote;

import com.neaniesoft.myweather.data.model.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mdpearce on 24/09/2016.
 */

public interface OpenWeatherMapService {
    @GET("weather")
    Call<CurrentWeather> searchForCurrentWeatherByCity(@Query("q") String cityString, @Query("appid") String apiKey);

    @GET("weather")
    Call<CurrentWeather> searchForCurrentWeatherByZip(@Query("zip") String zipString, @Query("appid") String apiKey);

    @GET("weather")
    Call<CurrentWeather> searchForCurrentWeatherByLatLon(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String apiKey);
}
