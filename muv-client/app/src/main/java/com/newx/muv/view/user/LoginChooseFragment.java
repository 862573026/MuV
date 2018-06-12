package com.newx.muv.view.user;


import android.databinding.Observable;
import android.databinding.ObservableField;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.ui.fragmentation.ISupportFragment;
import com.newx.muv.BR;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentLoginChooseBinding;
import com.newx.muv.proxy.MiddlewareProxy;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.LoginChooseVM;

/**
 * Created by xuzhijian on 2018/5/3 0003.
 */
@Route(path = FRAGMENT.LoginChooseFragment)
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
