package com.newx.camera;

import android.app.Application;
import android.util.Log;

import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.base.utils.utilcode.constant.PermissionConstants;
import com.newx.base.utils.utilcode.util.PermissionUtils;
import com.newx.base.utils.utilcode.util.Utils;

/**
 * Created by xuzhijian on 2018/6/5 0005.
 */

public class CameraApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("NewX","Camera CameraApp 初始化");
        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE)
                .request();

        com.newx.base.utils.utilcode.subutil.Utils.init(this);
        Utils.init(this);
        ARouter.setPlugin("");
        ARouter.init(this);
    }
}
