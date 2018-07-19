package debug;



import com.newx.api.router.WideRouter;
import com.newx.common.debug.NXLogAdapter;
import com.newx.base.framework.BaseApp;
import com.newx.common.frameworks.route.launcher.ARouter;
import com.newx.utils.logger.NXLog;
import com.newx.utils.utilcode.util.Utils;

/**
 * <p>类说明</p>
 *
 * @author 张华洋 2017/2/15 20:09
 * @version V1.2.0
 * @name GirlsApplication
 */
public class MainApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openDebug();
        ARouter.init(this);
        Utils.init(this);
        NXLog.addLogAdapter(new NXLogAdapter());
        com.newx.utils.utilcode.subutil.Utils.init(this);

    }

    @Override
    public void initializeAllProcessRouter() {
        WideRouter.registerLocalRouter("com.newx.muv.main",MainRouterConnectService.class);
//        WideRouter.registerLocalRouter("com.newx.muv.main:pic",PicRouterConnectService.class);
    }

    @Override
    protected void initializeLogic() {
        registerApplicationLogic("com.newx.muv.main",999, MainApplicationLogic.class);
//        registerApplicationLogic("com.newx.muv.main:pic",999, PicApplicationLogic.class);
    }

    @Override
    public boolean needMultipleProcess() {
        return true;
    }

}
