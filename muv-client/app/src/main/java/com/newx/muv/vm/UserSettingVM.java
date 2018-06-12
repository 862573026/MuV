package com.newx.muv.vm;

import android.content.Context;

import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.base.ui.fragmentation.SupportFragmentDelegate;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.helper.LoginHelper;
import com.newx.muv.view.route.ACTIVITY;


/**
 * Created by newx on 18-5-12.
 */

public class UserSettingVM extends NSBaseVM {

    private Context mContext;
    private LoginHelper mLoginHelper;

    public UserSettingVM(Context context) {
        mContext = context;
    }

    /**
     * 登出
     */
    public void logoutClick() {
        mLoginHelper = new LoginHelper();
        mLoginHelper.setOfflineStatus();

        ARouter.getInstance()
                .build(ACTIVITY.TempActivity)
                .navigation();
        exit(mContext);
    }
}
