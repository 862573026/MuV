package com.newx.camera.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.base.route.RP;
import com.newx.common.frameworks.route.launcher.ARouter;
import com.newx.common.frameworks.support.mvvm.NxBindingActivity;
import com.newx.ui.fragmentation.ISupportFragment;
import com.newx.utils.utilcode.constant.PermissionConstants;
import com.newx.utils.utilcode.util.PermissionUtils;
import com.newx.camera.R;
import com.newx.camera.databinding.ActivityRootBinding;

/**
 * Created by xuzhijian on 2018/6/5 0005.
 */

public class ActivityRoot extends NxBindingActivity<ActivityRootBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermissionUtils.permission(PermissionConstants.CAMERA,PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        ISupportFragment fragment = (ISupportFragment) ARouter.getInstance()
                                .build(RP.FragmentCameraRoot)
                                .navigation();

                        loadRootFragment(fragment);
                    }

                    @Override
                    public void onDenied() {

                    }
                })
                .request();


    }

    @Override
    public int initContentView() {
        return R.layout.activity_root;
    }


}
