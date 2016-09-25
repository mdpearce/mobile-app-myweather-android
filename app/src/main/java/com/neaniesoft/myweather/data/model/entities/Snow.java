package com.neaniesoft.myweather.data.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class Snow {
    @SerializedName("3h")
    Double last3Hours;

    public Double getLast3Hours() {
        return last3Hours;
    }

    public void setLast3Hours(Double last3Hours) {
        this.last3Hours = last3Hours;
    }
}
