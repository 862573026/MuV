package com.newx.muv.vm;

import android.databinding.ObservableField;

import com.newx.base.ui.fragmentation.SupportFragmentDelegate;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.helper.JumpHelper;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.proxy.MiddlewareProxy;

/**
 * Created by newx on 18-5-12.
 */

public class MeVM extends NSBaseVM {

    public ObservableField<String> username = new ObservableField<>();

    public MeVM() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void lazyLoad() {
        username.set(MiddlewareProxy.getRuntimeDataManager().currentUser.getUsername());
    }

    public void userSettingClick() {
        JumpHelper.fragment(FRAGMENT.UserSettingFragment)
                .navigation();
    }

}
