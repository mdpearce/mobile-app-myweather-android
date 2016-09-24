package com.neaniesoft.myweather.data.repository.remote;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by mdpearce on 24/09/2016.
 */
public class OpenWeatherMapSearchTest {

    private OpenWeatherMapSearch openWeatherMapSearch;

    @Before
    public void setUp() throws Exception {
        openWeatherMapSearch = new OpenWeatherMapSearch();
    }

    @Test
    public void openWeatherMapSearch_ChecksForEmpty() {
        OpenWeatherMapSearchRequest request = openWeatherMapSearch.getSearchRequest("");
        assertNull(request);

        request = openWeatherMapSearch.getSearchRequest("String");
        assertNotNull(request);
    }

    @Test
    public void openWeatherMapSearch_SplitsCityAndZip() {
        OpenWeatherMapSearchRequest request = openWeatherMapSearch.getSearchRequest("london");
        assertEquals("london", request.getCitySearch());
        assertNull(request.getZipCodeSearch());

        request = openWeatherMapSearch.getSearchRequest("90210");
        assertEquals("90210", request.getZipCodeSearch());
        assertNull(request.getCitySearch());

        request = openWeatherMapSearch.getSearchRequest("Sydney 2000");
        assertEquals("Sydney 2000", request.getCitySearch());
        assertNull(request.getZipCodeSearch());

        request = openWeatherMapSearch.getSearchRequest("90210BeverlyHills");
        assertEquals("90210BeverlyHills", request.getZipCodeSearch());
        assertNull(request.getCitySearch());

        request = openWeatherMapSearch.getSearchRequest("9BeverlyHills");
        assertEquals("9BeverlyHills", request.getZipCodeSearch());
        assertNull(request.getCitySearch());
    }

}