package com.newx.muv.ioc.util;

import android.content.Context;

import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.muv.ioc.ComponentHelper;
import com.newx.muv.utils.rx.rxbus.RxBus;

import javax.inject.Inject;

/**
 * Created by xuzhijian on 2018/5/4 0004.
 * Dagger2 控件
 */

public class ClientInject {

    @Inject
    public Context mApp;

    @Inject
    public RxBus mRxBus;

    @Inject
    public ARouter mARouter;

    public void onCreate() {
        ComponentHelper.getComponent().injectClient(this);
    }
}
