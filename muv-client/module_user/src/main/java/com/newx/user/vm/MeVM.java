package com.newx.user.vm;

import android.databinding.ObservableField;

import com.newx.base.proxy.MiddlewareProxy;
import com.newx.base.route.RP;
import com.newx.base.vm.NxBaseVM;
import com.newx.common.helper.JumpHelper;

/**
 * Created by newx on 18-5-12.
 */

public class MeVM extends NxBaseVM {

    public ObservableField<String> username = new ObservableField<>();

    public MeVM() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void lazyLoad() {
//        username.set(MiddlewareProxy.getRuntimeDataManager().currentUser.getUsername());
    }

    public void userSettingClick() {
        JumpHelper.fragment(RP.UserSettingFragment)
                .navigation();
    }

}
