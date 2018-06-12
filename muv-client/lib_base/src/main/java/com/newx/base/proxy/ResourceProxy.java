package com.newx.base.proxy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.newx.base.frameworks.app.BaseApp;

/**
 * Created by xuzhijian on 2018/3/30 0030.
 * 资源代理
 */

public class ResourceProxy {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private ResourceProxy() {
        throw new UnsupportedOperationException("You can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        ResourceProxy.mContext = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (mContext != null) {
            return mContext;
        }
        throw new NullPointerException("should be initialized in application");
    }

    public static BaseApp getApp() {
        if (mContext != null) {
            return (BaseApp) mContext;
        }
        throw new NullPointerException("should be initialized in application");
    }

    public static String getString(@StringRes int id) {
        return getContext().getResources().getString(id);
    }

    public static String getString(@StringRes int id, Object... formatArgs) {
        return getContext().getResources().getString(id, formatArgs);
    }

    public static int getColor(@ColorRes int id) {
        return getContext().getResources().getColor(id);
    }

    public static float getDimension(@DimenRes int id) {
        return getContext().getResources().getDimension(id);
    }

    public static Drawable getDrawable(@DrawableRes int id) {
        return getContext().getResources().getDrawable(id);
    }

//    public static Drawable getDrawable(int id, Resources.Theme theme) {
//        return App.getApp().getResources().getDrawable(id, theme);
//    }

    public static Resources getResources() {
        return getContext().getResources();
    }
}
