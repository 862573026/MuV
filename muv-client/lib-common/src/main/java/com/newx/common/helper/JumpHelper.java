package com.newx.common.helper;

import android.os.Bundle;

import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.Postcard;
import com.newx.common.frameworks.route.launcher.ARouter;


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
                .build(RP.NxSwipeBackActivity)
                .withString(RP.KEY_FRAGMENT, fragment);
    }

    public static Postcard fragment(String fragment, Bundle bundle) {
        return ARouter.getInstance()
                .build(RP.NxSwipeBackActivity)
                .withString(RP.KEY_FRAGMENT, fragment)
                .withBundle(RP.KEY_BUNDLE, bundle);
    }

    /**
     * 跳转 NFragmentActivity
     * @param fragment
     * @return
     */
    public static Postcard containerFragment(String fragment) {
        return ARouter.getInstance()
                .build(RP.NxFragmentActivity)
                .withString(RP.KEY_FRAGMENT, fragment);
    }

    public static Postcard containerFragment(String fragment, Bundle bundle) {
        return ARouter.getInstance()
                .build(RP.NxFragmentActivity)
                .withString(RP.KEY_FRAGMENT, fragment)
                .withBundle(RP.KEY_BUNDLE, bundle);
    }


}
