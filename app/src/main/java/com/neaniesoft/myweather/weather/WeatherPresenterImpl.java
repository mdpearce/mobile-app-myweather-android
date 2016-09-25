package com.neaniesoft.myweather.weather;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.neaniesoft.myweather.data.model.CurrentWeather;
import com.neaniesoft.myweather.data.repository.WeatherProvider;
import com.neaniesoft.myweather.prefs.LocationPrefs;

/**
 * Created by mdpearce on 25/09/2016.
 */

public class WeatherPresenterImpl implements WeatherPresenter {
    private final WeatherView mWeatherView;
    private final LocationPrefs mLocationPrefs;
    private final WeatherProvider mWeatherRepository;

    public WeatherPresenterImpl(@NonNull WeatherView view, @NonNull LocationPrefs locationPrefs, @NonNull WeatherProvider repository) {
        mWeatherView = view;
        mLocationPrefs = locationPrefs;
        mWeatherRepository = repository;
    }

    @Override
    public void start() {
        checkForLocation();
    }

    private void checkForLocation() {
        if (mLocationPrefs.useMyLocation()) {
            loadMyLocation();
        } else {
            String query = mLocationPrefs.getLatestSearch();
            if (query == null || query.length() < 1) {
                mWeatherView.launchSearchPage();
            } else {
                searchForWeather(query);
            }
        }
    }


    @Override
    public void loadMyLocation() {
    }

    @Override
    public void searchForWeather(String query) {
        mWeatherRepository.searchForCurrentWeather(query, new WeatherProvider.CurrentWeatherCallback() {
            @Override
            public void onCurrentWeatherReceived(@NonNull CurrentWeather currentWeather) {
                setWeatherData(currentWeather);
            }

            @Override
            public void onNoCurrentWeatherAvailable(@Nullable String error) {
                mWeatherView.showErrorNoWeatherData();
            }
        });
    }

    private void setWeatherData(@NonNull CurrentWeather currentWeather) {
        
    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {
        if (WeatherView.REQUEST_SEARCH == requestCode && Activity.RESULT_OK == resultCode) {
            checkForLocation();
        }
    }
}
