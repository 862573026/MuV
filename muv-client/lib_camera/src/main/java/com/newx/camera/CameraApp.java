package com.newx.camera;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.newx.base.frameworks.app.BaseApp;
import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.base.proxy.ResourceProxy;
import com.newx.base.utils.mobile.SPUtil;
import com.newx.base.utils.utilcode.constant.PermissionConstants;
import com.newx.base.utils.utilcode.util.PermissionUtils;
import com.newx.base.utils.utilcode.util.Utils;

/**
 * Created by xuzhijian on 2018/6/5 0005.
 */

public class CameraApp extends BaseApp{

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("NewX","Camera CameraApp 初始化");

        com.newx.base.utils.utilcode.subutil.Utils.init(this);
        Utils.init(this);
        SPUtil.init(this);

        ARouter.init(this);

        ResourceProxy.init(this);

        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE)
                .request();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this); //首次启动慢
    }
}
