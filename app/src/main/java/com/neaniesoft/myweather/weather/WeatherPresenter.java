package com.neaniesoft.myweather.weather;

import android.content.Intent;

import com.neaniesoft.myweather.BasePresenter;

/**
 * Created by mdpearce on 25/09/2016.
 */

public interface WeatherPresenter extends BasePresenter {
    void loadMyLocation();

    void searchForWeather(String query);

    void activityResult(int requestCode, int resultCode, Intent data);
}
