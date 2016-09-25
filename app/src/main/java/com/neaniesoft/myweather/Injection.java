package com.neaniesoft.myweather;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neaniesoft.myweather.data.repository.WeatherRepository;
import com.neaniesoft.myweather.data.repository.remote.OpenWeatherMapProvider;
import com.neaniesoft.myweather.data.repository.remote.OpenWeatherMapService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Simple Dependency Injection - no need for a full-blown framework for this task
 * Created by mdpearce on 24/09/2016.
 */

public class Injection {
    public static Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.OPENWEATHERMAP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .build();
        return retrofit;
    }

    public static Gson provideGson() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson;
    }

    public static OpenWeatherMapService provideOpenWeatherMapService() {
        return provideRetrofit().create(OpenWeatherMapService.class);
    }

    public static OpenWeatherMapProvider provideOpenWeatherMapProvider() {
        return new OpenWeatherMapProvider(provideOpenWeatherMapService());
    }

    public static WeatherRepository provideWeatherRepository() {
        return WeatherRepository.getInstance(Injection.provideOpenWeatherMapProvider());
    }
}
