package com.neaniesoft.myweather.data.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class Wind {
    Double speed;

    @SerializedName("deg")
    Integer directionDegrees;

    @SerializedName("gust")
    Double gustSpeed;
}
