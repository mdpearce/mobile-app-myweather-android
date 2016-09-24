package com.neaniesoft.myweather.data.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class WeatherConditions {
    @SerializedName("id")
    Integer conditionId;

    @SerializedName("main")
    String conditionsGroup;

    String description;

    @SerializedName("icon")
    String iconId;
}
