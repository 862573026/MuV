package com.newx.common.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.newx.common.proxy.ResourceProxy;
import com.newx.ui.NXToast;


/**
 * Created by xuzhijian on 2018/4/18 0018.
 */

public class ToastUtil {

    public static void showShort(CharSequence text){
        NXToast.normal(ResourceProxy.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(@StringRes int resId){
        NXToast.normal(ResourceProxy.getContext(),ResourceProxy.getString(resId), Toast.LENGTH_SHORT).show();
    }
}
