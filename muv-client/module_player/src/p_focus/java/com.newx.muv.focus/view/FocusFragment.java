package com.newx.muv.focus.view;

import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.BaseViewModel;
import com.newx.common.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.player.R;
import com.newx.player.databinding.FragmentFocusBinding;

/**
 * Created by xuzhijian on 2018/4/8 0008.
 *
 */
@Route(path = RP.FocusFragment)
public class FocusFragment extends NxMvvMFragment<FragmentFocusBinding,BaseViewModel> {

    @Override
    public int initContentView() {
        return R.layout.fragment_focus;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public BaseViewModel initVM() {
        return null;
    }
}
