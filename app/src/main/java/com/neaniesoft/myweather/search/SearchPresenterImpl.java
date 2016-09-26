package com.neaniesoft.myweather.search;

import android.content.pm.PackageManager;
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
        if (mSearchView.checkLocationPermission() != PackageManager.PERMISSION_GRANTED) {
            mSearchView.requestLocationPermission();
        } else {
            myLocationPermissionGranted();
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SearchView.PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                myLocationPermissionGranted();
            } else {
                myLocationPermissionDenied();
            }
        }
    }

    private void myLocationPermissionGranted() {
        mLocationPrefs.setUseMyLocation(true);
        mSearchView.finish();
    }

    private void myLocationPermissionDenied() {
        mLocationPrefs.setUseMyLocation(false);
    }
}
