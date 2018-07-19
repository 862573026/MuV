package com.newx.user.vm;


import android.databinding.ObservableField;

import com.newx.base.route.RP;
import com.newx.base.vm.NxBaseVM;


/**
 * Created by newx on 18-5-3.
 */

public class LoginChooseVM extends NxBaseVM {

    public ObservableField<String> mJumpPage = new ObservableField<>();

    public LoginChooseVM() {
    }

    public void toByUsername() {
        mJumpPage.set(RP.LoginByPswFragment);
    }

    public void toRegister() {
        mJumpPage.set(RP.RegisterFragment);
    }

}
