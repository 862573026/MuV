package com.newx.base.utils.view;

import android.support.v7.app.AppCompatDelegate;

/**
 * Created by xuzhijian on 2018/4/10 0010.
 * 主题工具
 */

public class ThemeUtil {

    public static void setNightMode(boolean night){
        if (night){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
