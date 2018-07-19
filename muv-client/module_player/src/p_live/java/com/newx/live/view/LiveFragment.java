package com.newx.live.view;

import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.BaseViewModel;
import com.newx.common.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.live.vm.LiveVM;
import com.newx.player.R;

/**
 * Created by newx on 18-4-8.
 */
@Route(path = RP.LiveFragment)
public class LiveFragment extends NxMvvMFragment {

    @Override
    public int initContentView() {
        return R.layout.fragment_live;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public BaseViewModel initVM() {
        return new LiveVM();
    }

}
