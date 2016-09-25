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

    public Integer getConditionId() {
        return conditionId;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionsGroup() {
        return conditionsGroup;
    }

    public void setConditionsGroup(String conditionsGroup) {
        this.conditionsGroup = conditionsGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }
}
