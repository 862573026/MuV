package com.newx.muv.base.page.activity;


import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.support.mvvm.NxBindingActivity;
import com.newx.muv.R;
import com.newx.muv.databinding.ActivityContainerBinding;
import com.newx.muv.view.route.ACTIVITY;

/**
 * Created by xuzhijian on 2018/5/21 0021.
 */

@Route(path = ACTIVITY.NFragmentActivity)
public class NxFragmentActivity extends NxBindingActivity<ActivityContainerBinding> {

    @Override
    public int initContentView() {
        return R.layout.activity_container;
    }

    @Override
    public int initContainerId() {
        return R.id.layout_container;
    }


}

