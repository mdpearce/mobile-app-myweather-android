package com.neaniesoft.myweather.search;

import android.support.annotation.NonNull;

import com.neaniesoft.myweather.BasePresenter;

/**
 * Created by mdpearce on 25/09/2016.
 */

public interface SearchPresenter extends BasePresenter {
    void myLocationRequested();
    void searchRequested();
    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
