package com.neaniesoft.myweather.weather;

import com.neaniesoft.myweather.BaseView;

/**
 * Created by mdpearce on 25/09/2016.
 */

public interface WeatherView extends BaseView<WeatherPresenter> {
    int REQUEST_SEARCH = 0;
    int PERMISSION_REQUEST_LOCATION = 10;

    void launchSearchPage();

    void showErrorNoWeatherData();

    void setLocation(String name, String countryCode);

    void setTempAndConditions(String temp, String conditions);

    void setWind(String speed, String direction);

    void setCloudiness(String cloudiness);

    void setPressure(String pressure);

    void setHumidity(String humidity);

    void setRain(String rain);

    void setSunrise(String sunrise);

    void setSunset(String sunset);

    void showProgressIndicator();

    void hideProgressIndicator();

    int checkLocationPermission();

    void requestLocationPermission();

    void requestMyLocation();

    void showErrorNoLocation();

    void showErrorNoPermission();
}
