package com.neaniesoft.myweather;

import android.app.Application;

/**
 * Created by mdpearce on 25/09/2016.
 */

public class MyWeatherApplication extends Application {
    private static MyWeatherApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static MyWeatherApplication getInstance() {
        return sApplication;
    }
}
