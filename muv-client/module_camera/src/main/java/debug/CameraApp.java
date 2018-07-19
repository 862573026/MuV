package debug;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.newx.base.framework.BaseApp;
import com.newx.common.frameworks.route.launcher.ARouter;
import com.newx.common.proxy.ResourceProxy;
import com.newx.utils.mobile.SPUtil;
import com.newx.utils.utilcode.constant.PermissionConstants;
import com.newx.utils.utilcode.util.PermissionUtils;
import com.newx.utils.utilcode.util.Utils;

/**
 * Created by xuzhijian on 2018/7/5 0005.
 */

public class CameraApp extends BaseApp{

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        SPUtil.init(this);

        ARouter.init(this);

        ResourceProxy.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this); //首次启动慢
    }
}
