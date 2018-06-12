package com.newx.base.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.newx.base.proxy.ResourceProxy;

/**
 * Created by xuzhijian on 2018/3/29 0029.
 */

public class BaseUtil {

    /**
     * 关闭软键盘
     *
     */
    public static void hideSystemSoftInput() {
        if (ResourceProxy.getApp().getCurrentActivity().get() == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) ResourceProxy.getApp().getCurrentActivity().get()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        Activity curentActivity = ResourceProxy.getApp().getCurrentActivity().get();
        if (imm != null && curentActivity != null) {
            View focusView = curentActivity.getCurrentFocus();
            if (focusView != null) {
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                }
            }
        }
    }
}
