package com.newx.common.frameworks.view;


import com.newx.base.route.RP;
import com.newx.common.R;
import com.newx.common.databinding.ActivityContainerBinding;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.NxBindingActivity;

/**
 * Created by xuzhijian on 2018/4/18 0018.
 * 滑动返回的Activity
 */
@Route(path = RP.NxSwipeBackActivity)
public class NxSwipeBackActivity extends NxBindingActivity<ActivityContainerBinding> {

    @Override
    public int initContentView() {
        return R.layout.activity_container;
    }

    @Override
    public int initContainerId() {
        return R.id.layout_container;
    }
}
