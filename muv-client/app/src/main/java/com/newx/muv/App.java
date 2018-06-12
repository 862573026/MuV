package com.newx.muv;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.newx.base.frameworks.app.BaseApp;
import com.newx.base.frameworks.util.log.NXLog;
import com.newx.base.ui.fragmentation.Fragmentation;
import com.newx.base.utils.mobile.SPUtil;
import com.newx.base.utils.utilcode.subutil.Utils;
import com.newx.base.utils.view.ThemeUtil;
import com.newx.service.net.CommunicationService;
import com.newx.muv.base.delegate.AppDelegate;
import com.newx.muv.base.debug.NXLogAdapter;
import com.newx.muv.base.def.SPConfig;

/**
 * Created by xuzhijian on 2018/4/10 0010.
 */

public class App extends BaseApp {

    public AppDelegate mAppDelegate;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppDelegate = new AppDelegate();

        mAppDelegate.onCreate(this);

        initDebugUtils();

        initFragmentation();

        initTheme();

        Utils.init(this);
        com.newx.base.utils.utilcode.util.Utils.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this); //首次启动慢
    }

    private void initDebugUtils() {
        NXLog.addLogAdapter(new NXLogAdapter());
    }

    private void initFragmentation() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出  默认NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                // 开发环境：true时，遇到异常："Can not perform this action after onSaveInstanceState!"时，抛出，并Crash;
                // 生产环境：false时，不抛出，不会Crash，会捕获，可以在handleException()里监听到
                .debug(BuildConfig.DEBUG) // 实际场景建议.debug(BuildConfig.DEBUG)
                // 生产环境时，捕获上述异常（避免crash），会捕获
                // 建议在回调处上传下面异常到崩溃监控服务器
                .handleException(e -> {
                    // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                    // Bugtags.sendException(e);
                })
                .install();
    }

    private void initTheme() {
        String theme = (String) SPUtil.get(SPConfig.KEY.KEY_THEME, SPConfig.VALUE.VALUE_THEME_DAY);
        switch (theme) {
            case SPConfig.VALUE.VALUE_THEME_DAY:
                ThemeUtil.setNightMode(false);
                break;
            case SPConfig.VALUE.VALUE_THEME_NIGHT:
                ThemeUtil.setNightMode(true);
                break;
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        stopService(new Intent(getCurrentActivity().get(), CommunicationService.class));
    }

}
