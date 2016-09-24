package com.neaniesoft.myweather.data.repository.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.neaniesoft.myweather.utils.TextUtils;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class OpenWeatherMapSearch {
    @Nullable public OpenWeatherMapSearchRequest getSearchRequest(@NonNull String searchString) {

        if (searchString == null || searchString.length() == 0) {
            return null;
        }

        String firstChar = searchString.substring(0, 1);
        if (TextUtils.isDigitsOnly(firstChar)) {
            return new OpenWeatherMapSearchRequest(null, searchString);
        } else {
            return new OpenWeatherMapSearchRequest(searchString, null);
        }
    }
}
