package com.newx.muv.view.tab.me;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.widget.bar.BarHide;
import com.newx.base.frameworks.widget.bar.ImmersionBar;
import com.newx.base.ui.fragmentation.ISupportFragment;
import com.newx.muv.R;
import com.newx.muv.view.RootFragment;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.proxy.MiddlewareProxy;

/**
 * Created by xuzhijian on 2018/5/19 0019.
 */
@Route(path = FRAGMENT.MeRootFragment)
public class MeRootFragment extends RootFragment {

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        loadRootFragment((ISupportFragment) MiddlewareProxy.findFragment(FRAGMENT.MeFragment));
    }
}
