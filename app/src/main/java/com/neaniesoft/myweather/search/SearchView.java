package com.neaniesoft.myweather.search;

import com.neaniesoft.myweather.BaseView;

/**
 * Created by mdpearce on 25/09/2016.
 */

public interface SearchView extends BaseView<SearchPresenter> {
    String getSearchQuery();
    void finish();

    void showErrorInvalidSearch();
}
