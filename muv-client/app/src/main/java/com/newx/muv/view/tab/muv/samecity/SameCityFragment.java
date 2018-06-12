package com.newx.muv.view.tab.muv.samecity;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.support.mvvm.BaseViewModel;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.view.route.FRAGMENT;

/**
 * Created by newx on 18-4-8.
 */
@Route(path = FRAGMENT.SameCityFragment)
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
