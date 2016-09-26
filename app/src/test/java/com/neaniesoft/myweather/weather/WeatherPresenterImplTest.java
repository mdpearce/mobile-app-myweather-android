package com.neaniesoft.myweather.weather;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import com.neaniesoft.myweather.data.model.CurrentWeather;
import com.neaniesoft.myweather.data.model.entities.Clouds;
import com.neaniesoft.myweather.data.model.entities.MainValues;
import com.neaniesoft.myweather.data.model.entities.Rain;
import com.neaniesoft.myweather.data.model.entities.Sys;
import com.neaniesoft.myweather.data.model.entities.WeatherConditions;
import com.neaniesoft.myweather.data.model.entities.Wind;
import com.neaniesoft.myweather.data.repository.WeatherProvider;
import com.neaniesoft.myweather.data.repository.WeatherRepository;
import com.neaniesoft.myweather.prefs.LocationPrefs;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by mdpearce on 26/09/2016.
 */
public class WeatherPresenterImplTest {

    @Mock
    WeatherView mWeatherView;

    @Mock
    LocationPrefs mLocationPrefs;

    @Mock
    WeatherRepository mRepository;

    @Captor
    private ArgumentCaptor<WeatherProvider.CurrentWeatherCallback> mCurrentWeatherCallbackCaptor;

    private WeatherPresenterImpl mWeatherPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mWeatherPresenter = new WeatherPresenterImpl(mWeatherView, mLocationPrefs, mRepository);
    }

    @Test
    public void WeatherPresenter_searchForMostRecentLocationOnStart() {
        when(mLocationPrefs.useMyLocation()).thenReturn(false);
        when(mLocationPrefs.getLatestSearch()).thenReturn("Sydney");
        mWeatherPresenter.start();
        verify(mRepository).searchForCurrentWeather(eq("Sydney"), mCurrentWeatherCallbackCaptor.capture());
    }

    @Test
    public void WeatherPresenter_checkForLocationPermissionOnStartIfNeeded() {
        when(mLocationPrefs.useMyLocation()).thenReturn(true);
        mWeatherPresenter.start();
        verify(mWeatherView).checkLocationPermission();
    }

    @Test
    public void WeatherPresenter_loadWeatherIntoView() {
        mWeatherPresenter.searchForWeather("Sydney");
        verify(mRepository).searchForCurrentWeather(eq("Sydney"), mCurrentWeatherCallbackCaptor.capture());
        mCurrentWeatherCallbackCaptor.getValue().onCurrentWeatherReceived(TEST_CURRENT_WEATHER());
        verify(mWeatherView).setWind("2.4", "N");
        verify(mWeatherView).setLocation("Sydney", "AU");
        verify(mWeatherView).setCloudiness("60");
    }

    @Test
    public void WeatherPresenter_requestMyLocationOnPermissionGranted() {
        mWeatherPresenter.onRequestPermissionsResult(
                WeatherView.PERMISSION_REQUEST_LOCATION,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                new int[] {PackageManager.PERMISSION_GRANTED});
        verify(mWeatherView).requestMyLocation();
    }

    @Test
    public void WeatherPresenter_showPermissionErrorOnPermissionDenied() {
        mWeatherPresenter.onRequestPermissionsResult(
                WeatherView.PERMISSION_REQUEST_LOCATION,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                new int[] {PackageManager.PERMISSION_DENIED});
        verify(mWeatherView).showErrorNoPermission();
    }

    @Test
    public void WeatherPresenter_showErrorOnNullLocation() {
        mWeatherPresenter.setMyLocation(null);
        verify(mWeatherView).showErrorNoLocation();
    }

    @Test
    public void WeatherPresenter_performNewSearchWhenSearchActivityFinished() {
        when(mLocationPrefs.useMyLocation()).thenReturn(false);
        when(mLocationPrefs.getLatestSearch()).thenReturn("Sydney");
        mWeatherPresenter.activityResult(WeatherView.REQUEST_SEARCH, Activity.RESULT_OK, null);
        verify(mRepository).searchForCurrentWeather(eq("Sydney"), mCurrentWeatherCallbackCaptor.capture());
    }

    @Test
    public void WeatherPresenter_doNotPerformNewSearchWhenSearchActivityCancelled() {
        mWeatherPresenter.activityResult(WeatherView.REQUEST_SEARCH, Activity.RESULT_CANCELED, null);
        verifyZeroInteractions(mRepository);
    }

    private static CurrentWeather TEST_CURRENT_WEATHER() {
        CurrentWeather c = new CurrentWeather();
        c.setCityName("Sydney");
        Sys sys = new Sys();
        sys.setCountry("AU");
        sys.setSunset(1474880100L);
        sys.setSunrise(29876068800L);
        c.setSys(sys);
        MainValues mainValues = new MainValues();
        mainValues.setTemp(20.0);
        mainValues.setPressure(1001.0);
        mainValues.setHumidity(75);
        c.setMainValues(mainValues);
        WeatherConditions conditions = new WeatherConditions();
        conditions.setConditionsGroup("Rain");
        c.setWeatherConditions(new WeatherConditions[]{conditions});
        Wind wind = new Wind();
        wind.setSpeed(2.4);
        wind.setDirectionDegrees(5.0);
        c.setWind(wind);
        Clouds clouds = new Clouds();
        clouds.setCloudiness(60);
        c.setClouds(clouds);
        Rain rain = new Rain();
        rain.setLast3Hours(2.4);
        c.setRain(rain);
        return c;
    }

}