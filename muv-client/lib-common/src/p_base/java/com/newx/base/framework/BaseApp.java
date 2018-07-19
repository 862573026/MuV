package com.newx.base.framework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.newx.api.MaApplication;
import com.newx.common.proxy.ResourceProxy;
import com.newx.utils.mobile.SPUtil;

import java.lang.ref.WeakReference;

/**
 * Created by xuzhijian on 2018/4/10 0010.
 * Application的基类
 */

public abstract class BaseApp extends Application {

    //为避免内存泄漏使用弱引用
    private WeakReference<Activity> mCurrentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks();

        SPUtil.init(this);
        ResourceProxy.init(this);
    }

    private void registerActivityLifecycleCallbacks() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    //do nothing
                }

                @Override
                public void onActivityStarted(Activity activity) {
                    //do nothing
                }

                @Override
                public void onActivityResumed(Activity activity) {
                    mCurrentActivity = new WeakReference<>(activity);
                }

                @Override
                public void onActivityPaused(Activity activity) {
                    //do nothing
                }

                @Override
                public void onActivityStopped(Activity activity) {
                    //do nothing
                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    //do nothing
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    //do nothing
                }
            });
        }
    }

    public WeakReference<Activity> getCurrentActivity() {
        return mCurrentActivity;
    }

}
