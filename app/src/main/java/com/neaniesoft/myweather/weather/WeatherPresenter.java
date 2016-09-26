package com.neaniesoft.myweather.weather;

import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;

import com.neaniesoft.myweather.BasePresenter;

/**
 * Created by mdpearce on 25/09/2016.
 */

public interface WeatherPresenter extends BasePresenter {
    void loadMyLocation();

    void searchForWeather(String query);

    void activityResult(int requestCode, int resultCode, Intent data);

    void searchPageRequested();

    void setMyLocation(Location location);

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
