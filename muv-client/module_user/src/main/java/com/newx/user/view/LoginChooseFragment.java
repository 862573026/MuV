package com.newx.user.view;


import android.databinding.Observable;
import android.databinding.ObservableField;

import com.newx.base.proxy.MiddlewareProxy;
import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.NxMvvMFragment;

import com.newx.ui.fragmentation.ISupportFragment;
import com.newx.user.BR;
import com.newx.user.R;
import com.newx.user.databinding.FragmentLoginChooseBinding;
import com.newx.user.vm.LoginChooseVM;


/**
 * Created by xuzhijian on 2018/5/3 0003.
 */
@Route(path = RP.LoginChooseFragment)
public class LoginChooseFragment extends NxMvvMFragment<FragmentLoginChooseBinding, LoginChooseVM> {

    @Override
    public int initVariableId() {
        return BR.loginChooseVM;
    }

    @Override
    public void initParam() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {
        mViewModel.mJumpPage.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                start((ISupportFragment) MiddlewareProxy.findFragment(((ObservableField) observable).get().toString()));
            }
        });
    }

    @Override
    public LoginChooseVM initVM() {
        return new LoginChooseVM();
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_login_choose;
    }
}
