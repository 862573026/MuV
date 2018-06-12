package com.newx.muv.view.tab.classify;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.support.mvvm.BaseViewModel;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.view.route.FRAGMENT;

/**
 * Created by xuzhijian on 2018/4/8 0008.
 */
@Route(path = FRAGMENT.ClassifyFragment)
public class ClassifyFragment extends NxMvvMFragment {

    @Override
    public int initContentView() {
        return R.layout.fragment_classify;
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
