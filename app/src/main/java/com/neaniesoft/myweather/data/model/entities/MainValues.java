package com.neaniesoft.myweather.data.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class MainValues {
    Double temp;
    Double pressure;
    Integer humidity;
    Double tempMin;
    Double tempMax;

    @SerializedName("sea_level")
    Double pressureSeaLevel;

    @SerializedName("grnd_level")
    Double pressureGroundLevel;
}
