package com.neaniesoft.myweather.data.repository.remote;

import android.support.annotation.NonNull;

import com.neaniesoft.myweather.BuildConfig;
import com.neaniesoft.myweather.data.model.CurrentWeather;
import com.neaniesoft.myweather.data.repository.WeatherProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mdpearce on 24/09/2016.
 */
public class OpenWeatherMapProvider implements WeatherProvider {

    private final OpenWeatherMapService openWeatherMapService;
    private final OpenWeatherMapSearch openWeatherMapSearch;

    public OpenWeatherMapProvider(OpenWeatherMapService service) {
        openWeatherMapService = service;
        openWeatherMapSearch = new OpenWeatherMapSearch();
    }

    @Override
    public void searchForCurrentWeather(@NonNull String searchString, @NonNull final CurrentWeatherCallback callback) {
        OpenWeatherMapSearchRequest searchRequest = openWeatherMapSearch.getSearchRequest(searchString);
        if (searchRequest == null) {
            return;
        }

        if (searchRequest.getCitySearch() != null) {
            enqueCitySearch(callback, searchRequest.getCitySearch());
        } else {
            enqueZipSearch(callback, searchRequest.getZipCodeSearch());
        }
    }

    private void enqueCitySearch(@NonNull final CurrentWeatherCallback callback, String searchRequest) {
        Call<CurrentWeather> call = openWeatherMapService.searchForCurrentWeatherByCity(searchRequest, BuildConfig.OPENWEATHERMAP_API_KEY);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (callback != null) {
                    if (response.isSuccessful()) {
                        callback.onCurrentWeatherReceived(response.body());
                    } else {
                        callback.onNoCurrentWeatherAvailable("Unsuccessful response from server");
                    }
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                if (callback != null) {
                    callback.onNoCurrentWeatherAvailable(t.getMessage());
                }
            }
        });
    }

    private void enqueZipSearch(@NonNull final CurrentWeatherCallback callback, String searchRequest) {
        Call<CurrentWeather> call = openWeatherMapService.searchForCurrentWeatherByZip(searchRequest, BuildConfig.OPENWEATHERMAP_API_KEY);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (callback != null) {
                    if (response.isSuccessful()) {
                        callback.onCurrentWeatherReceived(response.body());
                    } else {
                        callback.onNoCurrentWeatherAvailable("Unsuccessful response from server");
                    }
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                if (callback != null) {
                    callback.onNoCurrentWeatherAvailable(t.getMessage());
                }
            }
        });
    }

    @Override
    public void searchForCurrentWeather(double lat, double lon, @NonNull final CurrentWeatherCallback callback) {
        Call<CurrentWeather> call = openWeatherMapService.searchForCurrentWeatherByLatLon(String.valueOf(lat), String.valueOf(lon), BuildConfig.OPENWEATHERMAP_API_KEY);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (callback != null) {
                    if (response.isSuccessful()) {
                        callback.onCurrentWeatherReceived(response.body());
                    } else {
                        callback.onNoCurrentWeatherAvailable("Unsuccessful response from server");
                    }
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                if (callback != null) {
                    callback.onNoCurrentWeatherAvailable(t.getMessage());
                }
            }
        });
    }
}
