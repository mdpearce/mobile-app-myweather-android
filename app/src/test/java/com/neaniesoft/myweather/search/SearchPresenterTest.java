package com.neaniesoft.myweather.search;

import android.Manifest;
import android.content.pm.PackageManager;

import com.neaniesoft.myweather.prefs.LocationPrefs;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mdpearce on 26/09/2016.
 */
public class SearchPresenterTest {

    SearchPresenterImpl mSearchPresenter;

    @Mock
    SearchView mSearchView;

    @Mock
    LocationPrefs mLocationPrefs;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mSearchPresenter = new SearchPresenterImpl(mSearchView, mLocationPrefs);
    }

    @Test
    public void SearchPresenter_checksForLocationPermission() {
        when(mSearchView.checkLocationPermission()).thenReturn(PackageManager.PERMISSION_DENIED);
        mSearchPresenter.myLocationRequested();
        verify(mSearchView).checkLocationPermission();
        verify(mSearchView).requestLocationPermission();
    }

    @Test
    public void SearchPresenter_invalidSearchEmpty() {
        when(mSearchView.getSearchQuery()).thenReturn("");
        mSearchPresenter.searchRequested();
        verify(mSearchView).showErrorInvalidSearch();
    }

    @Test
    public void SearchPresenter_invalidSearchNull() {
        when(mSearchView.getSearchQuery()).thenReturn(null);
        mSearchPresenter.searchRequested();
        verify(mSearchView).showErrorInvalidSearch();
    }

    @Test
    public void SearchPresenter_savesValidSearch() {
        when(mSearchView.getSearchQuery()).thenReturn("Sydney");
        mSearchPresenter.searchRequested();
        verify(mLocationPrefs).setLatestSearch("Sydney");
    }

    @Test
    public void SearchPresenter_endsActivityOnValidSearch() {
        when(mSearchView.getSearchQuery()).thenReturn("Sydney");
        mSearchPresenter.searchRequested();
        verify(mSearchView).finish();
    }

    @Test
    public void SearchPresenter_savesUseMyLocationOnPermissionGranted() {
        mSearchPresenter.onRequestPermissionsResult(
                SearchView.PERMISSIONS_REQUEST_LOCATION,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                new int[] {PackageManager.PERMISSION_GRANTED});
        verify(mLocationPrefs).setUseMyLocation(true);
    }

    @Test
    public void SearchPresenter_clearsUseMyLocationOnPermissionDenied() {
        mSearchPresenter.onRequestPermissionsResult(
                SearchView.PERMISSIONS_REQUEST_LOCATION,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                new int[] {PackageManager.PERMISSION_DENIED});
        verify(mLocationPrefs).setUseMyLocation(false);
    }

    @Test
    public void SearchPresenter_endsActivityOnPermissionGranted() {
        mSearchPresenter.onRequestPermissionsResult(
                SearchView.PERMISSIONS_REQUEST_LOCATION,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                new int[] {PackageManager.PERMISSION_GRANTED});
        verify(mSearchView).finish();
    }
}