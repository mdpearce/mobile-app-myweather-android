package com.neaniesoft.myweather.data.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class Wind {
    Double speed;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDirectionDegrees() {
        return directionDegrees;
    }

    public void setDirectionDegrees(Double directionDegrees) {
        this.directionDegrees = directionDegrees;
    }

    public Double getGustSpeed() {
        return gustSpeed;
    }

    public void setGustSpeed(Double gustSpeed) {
        this.gustSpeed = gustSpeed;
    }

    @SerializedName("deg")
    Double directionDegrees;

    @SerializedName("gust")
    Double gustSpeed;
}
