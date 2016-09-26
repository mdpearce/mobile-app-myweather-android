package com.neaniesoft.myweather.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.neaniesoft.myweather.Injection;
import com.neaniesoft.myweather.R;
import com.neaniesoft.myweather.prefs.LocationPrefs;

public class WeatherActivity extends AppCompatActivity {

    private WeatherPresenter mWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        if (weatherFragment == null) {
            weatherFragment = WeatherFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment, weatherFragment)
                    .commit();
        }

        mWeatherPresenter = new WeatherPresenterImpl(weatherFragment,
                new LocationPrefs(getApplicationContext()),
                Injection.provideWeatherRepository());
    }
}
