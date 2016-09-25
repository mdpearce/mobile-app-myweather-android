package com.neaniesoft.myweather.weather;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neaniesoft.myweather.R;
import com.neaniesoft.myweather.search.SearchActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherFragment extends Fragment implements WeatherView {

    private WeatherPresenter mPresenter;

    public WeatherFragment() {
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void setPresenter(@NonNull WeatherPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void launchSearchPage() {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        startActivityForResult(intent, REQUEST_SEARCH);
    }

    @Override
    public void showErrorNoWeatherData() {
        if (getView() != null) {
            Snackbar.make(getView(), R.string.error_no_weather_data, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.activityResult(requestCode, resultCode, data);
    }
}
