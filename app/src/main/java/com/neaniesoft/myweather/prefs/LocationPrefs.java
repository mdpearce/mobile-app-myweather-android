package com.neaniesoft.myweather.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * Created by mdpearce on 25/09/2016.
 */

public class LocationPrefs {
    private final Context mContext;
    private static final String KEY_LATEST_SEARCH = "latest_search";
    private static final String KEY_USE_MY_LOCATION = "use_my_location";

    public LocationPrefs(Context context) {
        mContext = context.getApplicationContext();
    }

    private SharedPreferences prefs() {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    private SharedPreferences.Editor edit() {
        return prefs().edit();
    }

    public void setLatestSearch(String searchQuery) {
        edit().putString(KEY_LATEST_SEARCH, searchQuery).apply();
        setUseMyLocation(false);
    }

    public void setUseMyLocation(boolean useMyLocation) {
        edit().putBoolean(KEY_USE_MY_LOCATION, useMyLocation).apply();
    }

    @Nullable public String getLatestSearch() {
        return prefs().getString(KEY_LATEST_SEARCH, null);
    }

    public boolean useMyLocation() {
        return prefs().getBoolean(KEY_USE_MY_LOCATION, false);
    }
}
