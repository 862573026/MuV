package debug;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.newx.base.framework.BaseApp;
import com.newx.common.debug.NXLogAdapter;
import com.newx.common.frameworks.route.launcher.ARouter;
import com.newx.utils.logger.NXLog;
import com.newx.utils.utilcode.util.Utils;

/**
 * Created by xuzhijian on 2018/7/6 0006.
 */

public class PlayerApp extends BaseApp {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        ARouter.openDebug();
        ARouter.init(this);
        Utils.init(this);
        NXLog.addLogAdapter(new NXLogAdapter());

        MultiDex.install(this); //首次启动慢
    }
}
