package com.newx.base.utils.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.newx.base.proxy.ResourceProxy;

/**
 * Created by xuzhijian on 2018/3/30 0030.
 * 显示转换 工具类
 */

public class DisplayUtil {

    public static int windowWidth = 0;
    public static int windowHeight = 0;


    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 获取Window的宽度
     *
     * @return
     */
    public static int getWindowWidth() {
        int width = 0;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Activity context = ResourceProxy.getApp().getCurrentActivity().get();
        if (context != null) {
            context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            width = displayMetrics.widthPixels;
            if (width > 0) {
                windowWidth = width;
            }
        }
        return width;
    }

    /**
     * 获取Window的高度
     *
     * @return
     */
    public static int getWindowHeight() {
        int width = 0;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Activity context = ResourceProxy.getApp().getCurrentActivity().get();
        if (context != null) {
            context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            width = displayMetrics.heightPixels;
            if (width > 0) {
                windowHeight = width;
            }
        }
        return width;
    }

    /**
     * 获取屏幕大小
     *
     * @param context
     * @return
     */
    public static int[] getScreenPixelSize(Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        Activity activity;
        if (!(context instanceof Activity) && context instanceof ContextWrapper) {
            activity = (Activity) ((ContextWrapper) context).getBaseContext();
        } else {
            activity = (Activity) context;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
}
