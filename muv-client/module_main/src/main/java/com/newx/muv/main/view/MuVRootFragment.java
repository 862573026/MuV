package com.newx.muv.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.base.proxy.MiddlewareProxy;
import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.ui.fragmentation.ISupportFragment;


/**
 * Created by xuzhijian on 2018/5/19 0019.
 */
@Route(path = RP.MuVRootFragment)
public class MuVRootFragment extends RootFragment {

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        loadRootFragment((ISupportFragment) MiddlewareProxy.findFragment(RP.MuVFragment));
    }

}
