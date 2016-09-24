package com.neaniesoft.myweather.data.repository.remote;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class OpenWeatherMapSearchRequest {
    private String citySearch;
    private String zipCodeSearch;

    public OpenWeatherMapSearchRequest(String citySearch, String zipCodeSearch) {
        this.citySearch = citySearch;
        this.zipCodeSearch = zipCodeSearch;
    }

    public String getCitySearch() {
        return citySearch;
    }

    public String getZipCodeSearch() {
        return zipCodeSearch;
    }
}
