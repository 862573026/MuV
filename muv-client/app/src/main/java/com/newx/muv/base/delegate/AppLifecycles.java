package com.newx.muv.base.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by xuzhijian on 2018/5/2 0002.
 */

public interface AppLifecycles {
    void attachBaseContext(@NonNull Context base);

    void onCreate(@NonNull Application application);

    void onTerminate(@NonNull Application application);
}