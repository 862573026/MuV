package com.newx.base.frameworks.app;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import com.newx.base.proxy.ResourceProxy;
import com.newx.base.utils.mobile.SPUtil;

import java.lang.ref.WeakReference;

/**
 * Created by xuzhijian on 2018/4/10 0010.
 * Application的基类
 */

public abstract class BaseApp extends Application {

//    private static BaseApp mApp;

    //为避免内存泄漏使用弱引用
    private WeakReference<Activity> mCurrentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
//        mApp = this;
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

//    public static BaseApp getApp() {
//        return mApp;
//    }
}
