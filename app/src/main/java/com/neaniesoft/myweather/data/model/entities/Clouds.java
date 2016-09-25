package com.neaniesoft.myweather.data.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class Clouds {
    @SerializedName("all")
    Integer cloudiness;

    public Integer getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(Integer cloudiness) {
        this.cloudiness = cloudiness;
    }
}
