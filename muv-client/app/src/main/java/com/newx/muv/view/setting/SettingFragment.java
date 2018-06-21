package com.newx.muv.view.setting;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.widget.bar.ImmersionBar;
import com.newx.muv.BR;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentSettingBinding;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.SettingVM;

/**
 * Created by xuzhijian on 2018/5/11 0011.
 */
@Route(path = FRAGMENT.SettingFragment)

public class SettingFragment extends NxMvvMFragment<FragmentSettingBinding, SettingVM> {
    @Override
    public int initVariableId() {
        return BR.settingVM;
    }

    @Override
    public SettingVM initVM() {
        return new SettingVM(getContext());
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        _mToolbarHelper.getToolbar().setTitle(R.string.setting_text);
    }

    @Override
    public boolean showToolbar() {
        return true;
    }

}
