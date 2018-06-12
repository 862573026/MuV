package com.newx.muv.ioc.util;

import com.newx.muv.ioc.ComponentHelper;

/**
 * Created by xuzhijian on 2018/5/7 0007.
 * Dragger2 代理
 */

public class InjectProxy {

    private InjectConfig mConfig;
    public ClientInject mClient;

    public InjectProxy(InjectConfig config) {
        mConfig = config;
    }

    public void onCreate() {
        if (mConfig.clientInject) {
            mClient = new ClientInject();
            ComponentHelper.getComponent().injectClient(mClient);
        }

    }
}
