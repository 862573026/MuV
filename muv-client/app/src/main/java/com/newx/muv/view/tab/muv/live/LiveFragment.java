package com.newx.muv.view.tab.muv.live;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.support.mvvm.BaseViewModel;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.LiveVM;

/**
 * Created by newx on 18-4-8.
 */
@Route(path = FRAGMENT.LiveFragment)
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

//    @Override
//    protected void immersionInit() {
//        super.immersionInit();
//        ImmersionBar.with(this)
//                .transparentBar()
//                .fullScreen(true)
//                .init();
//    }
}
