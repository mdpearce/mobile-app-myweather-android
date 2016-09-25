package com.neaniesoft.myweather.weather;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.neaniesoft.myweather.data.model.CurrentWeather;
import com.neaniesoft.myweather.data.model.entities.Clouds;
import com.neaniesoft.myweather.data.model.entities.MainValues;
import com.neaniesoft.myweather.data.model.entities.Rain;
import com.neaniesoft.myweather.data.model.entities.WeatherConditions;
import com.neaniesoft.myweather.data.model.entities.Wind;
import com.neaniesoft.myweather.data.repository.WeatherProvider;
import com.neaniesoft.myweather.prefs.LocationPrefs;
import com.neaniesoft.myweather.utils.TextUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mdpearce on 25/09/2016.
 */

public class WeatherPresenterImpl implements WeatherPresenter {
    private final WeatherView mWeatherView;
    private final LocationPrefs mLocationPrefs;
    private final WeatherProvider mWeatherRepository;
    private final DateFormat mSunDateFormat;

    public WeatherPresenterImpl(@NonNull WeatherView view, @NonNull LocationPrefs locationPrefs, @NonNull WeatherProvider repository) {
        mWeatherView = view;
        mWeatherView.setPresenter(this);
        mLocationPrefs = locationPrefs;
        mWeatherRepository = repository;
        mSunDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
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
        mWeatherView.setLocation(currentWeather.getCityName(),
                currentWeather.getSys() != null ? currentWeather.getSys().getCountry() : null);
        MainValues mainValues = currentWeather.getMainValues();
        WeatherConditions conditions = currentWeather.getPrimaryWeatherConditions();
        setMainValues(mainValues, conditions);
        setWind(currentWeather.getWind());
        setClouds(currentWeather.getClouds());
        setRain(currentWeather.getRain());
        setSunrise(currentWeather.getSunrise());
        setSunset(currentWeather.getSunset());
    }

    private void setMainValues(MainValues mainValues, WeatherConditions conditions) {
        if (mainValues != null) {
            mWeatherView.setTempAndConditions(
                    TextUtils.formatDoubleValue(mainValues.getTemp()),
                    conditions != null ? conditions.getConditionsGroup() : null);
            mWeatherView.setPressure(TextUtils.formatDoubleValue(mainValues.getPressure()));
            mWeatherView.setHumidity(TextUtils.formatIntValue(mainValues.getHumidity()));
        } else {
            mWeatherView.setTempAndConditions(null, null);
            mWeatherView.setPressure(null);
            mWeatherView.setHumidity(null);
        }
    }

    private void setWind(Wind wind) {
        if (wind != null) {
            mWeatherView.setWind(TextUtils.formatDoubleValue(wind.getSpeed()),
                    TextUtils.formatDirection(wind.getDirectionDegrees())
            );
        } else {
            mWeatherView.setWind(null, null);
        }
    }

    private void setClouds(Clouds clouds) {
        if (clouds != null) {
            mWeatherView.setCloudiness(TextUtils.formatIntValue(clouds.getCloudiness()));
        } else {
            mWeatherView.setCloudiness(null);
        }
    }

    private void setRain(Rain rain) {
        if (rain != null) {
            mWeatherView.setRain(TextUtils.formatDoubleValue(rain.getLast3Hours()));
        } else {
            mWeatherView.setRain(null);
        }
    }

    private void setSunrise(Long sunrise) {
        if (sunrise != null) {
            Date sunriseDate = new Date(sunrise * 1000);
            mWeatherView.setSunrise(mSunDateFormat.format(sunriseDate));
        } else {
            mWeatherView.setSunrise(null);
        }
    }

    private void setSunset(Long sunset) {
        if (sunset != null) {
            Date sunsetDate = new Date(sunset * 1000);
            mWeatherView.setSunset(mSunDateFormat.format(sunsetDate));
        } else {
            mWeatherView.setSunset(null);
        }
    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {
        if (WeatherView.REQUEST_SEARCH == requestCode && Activity.RESULT_OK == resultCode) {
            checkForLocation();
        }
    }
}
