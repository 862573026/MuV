package com.newx.muv.host;

import android.util.Log;

import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.base.proxy.ResourceProxy;
import com.newx.base.utils.utilcode.util.Utils;
import com.qihoo360.replugin.RePluginApplication;

/**
 * Created by newx on 18-6-3.
 */

public class HostApp extends RePluginApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("NewX","Host App 初始化");
        ResourceProxy.init(this);
        Utils.init(this);
        ARouter.init(this);
    }
}
