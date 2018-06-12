package com.newx.muv.vm;


import android.databinding.ObservableField;

import com.newx.base.ui.fragmentation.SupportFragmentDelegate;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.view.route.FRAGMENT;

import java.util.Observable;

/**
 * Created by newx on 18-5-3.
 */

public class LoginChooseVM extends NSBaseVM {

    public ObservableField<String> mJumpPage = new ObservableField<>();

    public LoginChooseVM() {
    }

    public void toByUsername() {
        mJumpPage.set(FRAGMENT.LoginByPswFragment);
    }

    public void toRegister() {
        mJumpPage.set(FRAGMENT.RegisterFragment);
    }


}
