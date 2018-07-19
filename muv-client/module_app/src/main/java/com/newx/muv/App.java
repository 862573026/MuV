package com.newx.muv;

import android.app.Application;

import com.newx.common.debug.NXLogAdapter;
import com.newx.common.frameworks.route.launcher.ARouter;
import com.newx.common.proxy.ResourceProxy;
import com.newx.utils.logger.NXLog;
import com.newx.utils.utilcode.util.Utils;

/**
 * Created by xuzhijian on 2018/7/9 0009.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openDebug();
        ARouter.init(this);
        Utils.init(this);
        NXLog.addLogAdapter(new NXLogAdapter());
        Utils.init(this);
        ResourceProxy.init(this);
    }
}
