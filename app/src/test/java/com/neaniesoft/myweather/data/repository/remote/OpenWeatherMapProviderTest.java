package com.neaniesoft.myweather.data.repository.remote;

import com.neaniesoft.myweather.BuildConfig;
import com.neaniesoft.myweather.data.model.CurrentWeather;
import com.neaniesoft.myweather.data.repository.WeatherProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by mdpearce on 24/09/2016.
 */
public class OpenWeatherMapProviderTest {

    private OpenWeatherMapProvider openWeatherMapProvider;

    @Mock
    OpenWeatherMapService openWeatherMapService;

    @Mock
    Call<CurrentWeather> call;

    @Mock
    WeatherProvider.CurrentWeatherCallback currentWeatherCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        openWeatherMapProvider = new OpenWeatherMapProvider(openWeatherMapService);

        when(openWeatherMapService.searchForCurrentWeatherByCity(anyString(), anyString())).thenReturn(call);
        when(openWeatherMapService.searchForCurrentWeatherByZip(anyString(), anyString())).thenReturn(call);
        when(openWeatherMapService.searchForCurrentWeatherByLatLon(anyString(), anyString(), anyString())).thenReturn(call);

    }

    @Test
    public void OpenWeatherMapProvider_HitsServiceForCity() {
        openWeatherMapProvider.searchForCurrentWeather("Sydney", currentWeatherCallback);
        verify(openWeatherMapService).searchForCurrentWeatherByCity("Sydney", BuildConfig.OPENWEATHERMAP_API_KEY);
        verify(call).enqueue(ArgumentCaptor.forClass(Callback.class).capture());
    }

    @Test
    public void OpenWeatherMapProvider_HitsServiceForLatLon() {
        openWeatherMapProvider.searchForCurrentWeather(0d, 0d, currentWeatherCallback);
        verify(openWeatherMapService).searchForCurrentWeatherByLatLon("0.0", "0.0", BuildConfig.OPENWEATHERMAP_API_KEY);
        verify(call).enqueue(ArgumentCaptor.forClass(Callback.class).capture());
    }

    @Test
    public void OpenWeatherMapProvider_HitsServiceForZip() {
        openWeatherMapProvider.searchForCurrentWeather("90210", currentWeatherCallback);
        verify(openWeatherMapService).searchForCurrentWeatherByZip("90210", BuildConfig.OPENWEATHERMAP_API_KEY);
        verify(call).enqueue(ArgumentCaptor.forClass(Callback.class).capture());
    }

    @Test
    public void OpenWeatherMapProvider_DoesntHitServiceForEmptySearch() {
        openWeatherMapProvider.searchForCurrentWeather("", currentWeatherCallback);
        verifyZeroInteractions(openWeatherMapService);
        verifyZeroInteractions(call);
    }
}