package debug;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.newx.base.framework.BaseApp;

/**
 * Created by xuzhijian on 2018/7/11 0011.
 */

public class UserApp extends BaseApp {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this); //首次启动慢
    }
}
