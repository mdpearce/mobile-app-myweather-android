package com.neaniesoft.myweather.weather;

import com.neaniesoft.myweather.BaseView;

/**
 * Created by mdpearce on 25/09/2016.
 */

public interface WeatherView extends BaseView<WeatherPresenter> {
    int REQUEST_SEARCH = 0;

    void launchSearchPage();

    void showErrorNoWeatherData();
}
