package com.newx.camera.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.android.arouter.routes.ARouter$$Group$$camera;
import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.base.ui.fragmentation.SupportActivity;
import com.newx.base.ui.fragmentation.SupportFragment;
import com.newx.base.utils.utilcode.constant.PermissionConstants;
import com.newx.base.utils.utilcode.util.PermissionUtils;
import com.newx.base.utils.utilcode.util.Utils;
import com.newx.camera.R;
import com.newx.muv.view.route.FRAGMENT;

/**
 * Created by xuzhijian on 2018/6/5 0005.
 */

public class ActivityRoot extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        Utils.init(this);

        PermissionUtils.permission(PermissionConstants.CAMERA,PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        SupportFragment fragment = (SupportFragment) ARouter.getInstance()
                                .build(FRAGMENT.FragmentCamera)
                                .navigation();

                        ARouter$$Group$$camera camera = new ARouter$$Group$$camera();

                        loadRootFragment(R.id.layout_root,fragment);
                    }

                    @Override
                    public void onDenied() {

                    }
                })
                .request();


    }
}
