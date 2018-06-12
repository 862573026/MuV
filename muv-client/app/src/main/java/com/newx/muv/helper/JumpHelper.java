package com.newx.muv.helper;

import android.os.Bundle;

import com.newx.base.frameworks.route.facade.Postcard;
import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.muv.view.route.ACTIVITY;
import com.newx.muv.view.route.BUNDLE;
import com.newx.muv.view.route.FRAGMENT;

/**
 * Created by xuzhijian on 2018/5/21 0021.
 * 跳转帮助类
 */

public class JumpHelper {

    /**
     * 调到SwipeBackActivity的 fragment
     *
     * @param fragment
     * @return
     */
    public static Postcard fragment(String fragment) {
        return ARouter.getInstance()
                .build(ACTIVITY.NSwipeBackActivity)
                .withString(FRAGMENT.KEY, fragment);
    }

    public static Postcard fragment(String fragment, Bundle bundle) {
        return ARouter.getInstance()
                .build(ACTIVITY.NSwipeBackActivity)
                .withString(FRAGMENT.KEY, fragment)
                .withBundle(BUNDLE.KEY, bundle);
    }

    /**
     * 跳转 NFragmentActivity
     * @param fragment
     * @return
     */
    public static Postcard containerFragment(String fragment) {
        return ARouter.getInstance()
                .build(ACTIVITY.NFragmentActivity)
                .withString(FRAGMENT.KEY, fragment);
    }

    public static Postcard containerFragment(String fragment, Bundle bundle) {
        return ARouter.getInstance()
                .build(ACTIVITY.NFragmentActivity)
                .withString(FRAGMENT.KEY, fragment)
                .withBundle(BUNDLE.KEY, bundle);
    }


}
