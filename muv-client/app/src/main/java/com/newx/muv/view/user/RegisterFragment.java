package com.newx.muv.view.user;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.muv.BR;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentRegisterBinding;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.RegisterVM;

/**
 * Created by xuzhijian on 2018/5/4 0004.
 */
@Route(path = FRAGMENT.RegisterFragment)
public class RegisterFragment extends NxMvvMFragment<FragmentRegisterBinding, RegisterVM> {

    @Override
    public int initContentView() {
        return R.layout.fragment_register;
    }

    @Override
    public int initVariableId() {
        return BR.registerVM;
    }

    @Override
    public RegisterVM initVM() {
        return new RegisterVM(getContext());
    }

}
