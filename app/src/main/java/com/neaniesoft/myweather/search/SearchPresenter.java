package com.neaniesoft.myweather.search;

import com.neaniesoft.myweather.BasePresenter;

/**
 * Created by mdpearce on 25/09/2016.
 */

public interface SearchPresenter extends BasePresenter {
    void myLocationRequested();
    void searchRequested();
}
