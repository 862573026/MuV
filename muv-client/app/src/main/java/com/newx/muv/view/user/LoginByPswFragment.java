package com.newx.muv.view.user;

import com.android.databinding.library.baseAdapters.BR;
import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentLoginPswBinding;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.LoginByPswVM;

/**
 * Created by xuzhijian on 2018/5/3 0003.
 */
@Route(path = FRAGMENT.LoginByPswFragment)
public class LoginByPswFragment extends NxMvvMFragment<FragmentLoginPswBinding, LoginByPswVM> {

    @Override
    public int initContentView() {
        return R.layout.fragment_login_psw;
    }

    @Override
    public int initVariableId() {
        return BR.loginVM;
    }


    @Override
    public LoginByPswVM initVM() {
        return new LoginByPswVM(getContext());
    }


}
