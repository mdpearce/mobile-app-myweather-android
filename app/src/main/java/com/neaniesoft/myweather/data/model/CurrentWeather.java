package com.neaniesoft.myweather.data.model;

import com.google.gson.annotations.SerializedName;
import com.neaniesoft.myweather.data.model.entities.Clouds;
import com.neaniesoft.myweather.data.model.entities.CoOrd;
import com.neaniesoft.myweather.data.model.entities.MainValues;
import com.neaniesoft.myweather.data.model.entities.Rain;
import com.neaniesoft.myweather.data.model.entities.Snow;
import com.neaniesoft.myweather.data.model.entities.Sys;
import com.neaniesoft.myweather.data.model.entities.WeatherConditions;
import com.neaniesoft.myweather.data.model.entities.Wind;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class CurrentWeather {
    @SerializedName("coord")
    CoOrd LocationCoOrds;

    @SerializedName("weather")
    WeatherConditions weatherConditions;

    @SerializedName("main")
    MainValues mainValues;

    Wind wind;

    Clouds clouds;

    Rain rain;

    Snow snow;

    @SerializedName("dt")
    Long timeOfData;

    Sys sys;

    @SerializedName("id")
    Long cityId;

    @SerializedName("name")
    String cityName;
}
