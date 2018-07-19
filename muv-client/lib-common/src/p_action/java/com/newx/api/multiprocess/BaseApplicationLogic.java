package com.newx.api.multiprocess;

import android.content.res.Configuration;
import android.support.annotation.NonNull;

import com.newx.api.MaApplication;


/**
 * Created by wanglei on 2016/11/25.
 */

public class BaseApplicationLogic {
    protected MaApplication mApplication;
    public BaseApplicationLogic() {
    }

    public void setApplication(@NonNull MaApplication application) {
        mApplication = application;
    }

    public void onCreate() {
    }

    public void onTerminate() {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int level) {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }
}
