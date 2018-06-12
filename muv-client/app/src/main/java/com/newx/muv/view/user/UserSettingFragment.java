package com.newx.muv.view.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.muv.BR;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentUserSettingBinding;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.UserSettingVM;

/**
 * Created by newx on 18-5-12.
 */
@Route(path = FRAGMENT.UserSettingFragment)
public class UserSettingFragment extends NxMvvMFragment<FragmentUserSettingBinding, UserSettingVM> {


    @Override
    public int initVariableId() {
        return BR.userSettingVM;
    }

    @Override
    public UserSettingVM initVM() {
        return new UserSettingVM(getContext());
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_user_setting;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void initToolbar() {
//        _mToolbar.setCenterTitle(R.string.user_setting_text);

    }

    @Override
    public boolean showToolbar() {
        return true;
    }
}
