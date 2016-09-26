package com.neaniesoft.myweather.utils;

import android.support.annotation.Nullable;

import java.util.Locale;

/**
 * Created by mdpearce on 24/09/2016.
 */

public class TextUtils {
    /**
     * Taken from Android source - saves having to include Android dependencies in unit tests
     * Returns whether the given CharSequence contains only digits.
     */
    public static boolean isDigitsOnly(CharSequence str) {
        final int len = str.length();
        for (int cp, i = 0; i < len; i += Character.charCount(cp)) {
            cp = Character.codePointAt(str, i);
            if (!Character.isDigit(cp)) {
                return false;
            }
        }
        return true;
    }

    public static @Nullable String formatDoubleValue(Double value) {
        if (value != null) {
            return String.format(Locale.getDefault(), "%.1f", value);
        }
        return null;
    }

    public static @Nullable String formatIntValue(Integer value) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    // Basic degrees -> cardinal conversion from http://stackoverflow.com/a/25349774/765286
    public static String formatDirection(Double directionDegrees) {
        if (directionDegrees == null) {
            return null;
        }
        double bearing = directionDegrees;
        if (bearing < 0 && bearing > -180) {
            bearing = 360.0 + bearing;
        }
        if (bearing > 360 || bearing < -180) {
            return "Unknown";
        }
        return sDirections[(int) Math.floor(((bearing + 11.25) % 360) / 22.5)];
    }

    private static String sDirections[] = {
            "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
            "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW",
            "N"};


}
