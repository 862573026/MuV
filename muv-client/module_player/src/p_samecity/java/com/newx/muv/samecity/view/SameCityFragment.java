package com.newx.muv.samecity.view;

import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.BaseViewModel;
import com.newx.common.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.player.R;

/**
 * Created by newx on 18-4-8.
 */
@Route(path = RP.SameCityFragment)
public class SameCityFragment extends NxMvvMFragment {


    @Override
    public int initContentView() {
        return R.layout.fragment_samecity;
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
