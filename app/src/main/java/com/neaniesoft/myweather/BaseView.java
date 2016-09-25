package com.neaniesoft.myweather;

import android.support.annotation.NonNull;

/**
 * Created by mdpearce on 25/09/2016.
 */

public interface BaseView<T> {
    void setPresenter(@NonNull T presenter);
}
