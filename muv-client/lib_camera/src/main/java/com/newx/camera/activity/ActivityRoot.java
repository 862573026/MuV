package com.newx.camera.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.view.View;

import com.newx.android.arouter.routes.ARouter$$Group$$camera;
import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.base.frameworks.support.mvvm.NxBindingActivity;
import com.newx.base.ui.fragmentation.ISupportFragment;
import com.newx.base.ui.fragmentation.SupportActivity;
import com.newx.base.ui.fragmentation.SupportFragment;
import com.newx.base.utils.utilcode.constant.PermissionConstants;
import com.newx.base.utils.utilcode.util.PermissionUtils;
import com.newx.base.utils.utilcode.util.Utils;
import com.newx.camera.R;
import com.newx.camera.databinding.ActivityRootBinding;
import com.newx.muv.view.route.FRAGMENT;

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
                                .build(FRAGMENT.FragmentRoot)
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
