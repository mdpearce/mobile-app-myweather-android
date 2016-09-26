package com.neaniesoft.myweather.weather;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.neaniesoft.myweather.R;
import com.neaniesoft.myweather.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherFragment extends Fragment implements WeatherView, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private WeatherPresenter mPresenter;

    @BindView(R.id.text_label_location)
    TextView textLocation;

    @BindView(R.id.text_wind)
    TextView textWind;

    @BindView(R.id.text_cloudiness)
    TextView textCloudiness;

    @BindView(R.id.text_pressure)
    TextView textPressure;

    @BindView(R.id.text_humidity)
    TextView textHumidity;

    @BindView(R.id.text_rain)
    TextView textRain;

    @BindView(R.id.text_sunrise)
    TextView textSunrise;

    @BindView(R.id.text_sunset)
    TextView textSunset;

    @BindView(R.id.text_temp_and_conditions)
    TextView textTempAndConditions;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Unbinder mUnbinder;

    private GoogleApiClient mGoogleApiClient;

    private boolean waitingForLocation;

    public WeatherFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_weather, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.action_search == item.getItemId()) {
            mPresenter.searchPageRequested();
            return true;
        }
        return false;
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_weather, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        getGoogleApiClient().connect();
        mPresenter.start();
    }

    @Override
    public void onStop() {
        getGoogleApiClient().disconnect();
        super.onStop();
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
    public void setLocation(String name, String countryCode) {
        if (name != null) {
            textLocation.setText(getString(R.string.label_location, name, countryCode != null ? countryCode : "-"));
        } else {
            textLocation.setText(R.string.weather);
        }
    }

    @Override
    public void setTempAndConditions(String temp, String conditions) {
        if (temp != null) {
            textTempAndConditions.setText(
                    getString(R.string.weather_temp_and_conditions, temp, conditions)
            );
        } else {
            textTempAndConditions.setText(R.string.blank);
        }
    }

    @Override
    public void setWind(String speed, String direction) {
        if (speed != null) {
            textWind.setText(getString(R.string.weather_wind, speed, direction));
        } else {
            textWind.setText(R.string.blank);
        }
    }

    @Override
    public void setCloudiness(String cloudiness) {
        if (cloudiness != null) {
            textCloudiness.setText(getString(R.string.weather_cloudiness, cloudiness));
        } else {
            textCloudiness.setText(R.string.blank);
        }
    }

    @Override
    public void setPressure(String pressure) {
        if (pressure != null) {
            textPressure.setText(getString(R.string.weather_pressure, pressure));
        } else {
            textPressure.setText(R.string.blank);
        }
    }

    @Override
    public void setHumidity(String humidity) {
        if (humidity != null) {
            textHumidity.setText(getString(R.string.weather_humidity, humidity));
        } else {
            textHumidity.setText(R.string.blank);
        }
    }

    @Override
    public void setRain(String rain) {
        if (rain != null) {
            textRain.setText(getString(R.string.weather_rain, rain));
        } else {
            textRain.setText(R.string.blank);
        }
    }

    @Override
    public void setSunrise(String sunrise) {
        if (sunrise != null) {
            textSunrise.setText(sunrise);
        } else {
            textSunrise.setText(R.string.blank);
        }
    }

    @Override
    public void setSunset(String sunset) {
        if (sunset != null) {
            textSunset.setText(sunset);
        } else {
            textSunset.setText(R.string.blank);
        }
    }

    @Override
    public void showProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndicator() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public int checkLocationPermission() {
        return ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void requestLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION);
    }

    @Override
    public void requestMyLocation() {
        if (getGoogleApiClient().isConnected()) {
            sendMyLocationToPresenter();
        } else {
            waitingForLocation = true;
            if (!getGoogleApiClient().isConnecting()) {
                getGoogleApiClient().connect();
            }
        }
    }

    @Override
    public void showErrorNoLocation() {
        if (getView() != null) {
            Snackbar.make(getView(), R.string.error_no_location, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void showErrorNoPermission() {
        if (getView() != null) {
            Snackbar.make(getView(), R.string.error_no_permission, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.activityResult(requestCode, resultCode, data);
    }

    private GoogleApiClient getGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        return mGoogleApiClient;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (waitingForLocation) {
            waitingForLocation = false;
            sendMyLocationToPresenter();
        }
    }

    private void sendMyLocationToPresenter() {
        // Double check permission on the off chance that this is called when permission is denied
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mPresenter.setMyLocation(LocationServices.FusedLocationApi.getLastLocation(getGoogleApiClient()));
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
