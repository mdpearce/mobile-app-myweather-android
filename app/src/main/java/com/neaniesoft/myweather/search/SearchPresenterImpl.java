package com.neaniesoft.myweather.search;

import android.support.annotation.NonNull;

import com.neaniesoft.myweather.prefs.LocationPrefs;

/**
 * Created by mdpearce on 25/09/2016.
 */

public class SearchPresenterImpl implements SearchPresenter {

    private final SearchView mSearchView;
    private final LocationPrefs mLocationPrefs;

    public SearchPresenterImpl(@NonNull SearchView searchView, @NonNull LocationPrefs locationPrefs) {
        mSearchView = searchView;
        mLocationPrefs = locationPrefs;
        mSearchView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void myLocationRequested() {
    }

    @Override
    public void searchRequested() {
        String searchQuery = mSearchView.getSearchQuery();
        if (searchQuery == null || searchQuery.length() < 1) {
            mSearchView.showErrorInvalidSearch();
        } else {
            mLocationPrefs.setLatestSearch(searchQuery.trim());
            mSearchView.finish();
        }
    }
}
