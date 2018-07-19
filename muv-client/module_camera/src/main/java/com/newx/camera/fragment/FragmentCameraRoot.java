package com.newx.camera.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.base.route.RP;
import com.newx.camera.databinding.FragmentCameraRootBinding;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.route.launcher.ARouter;
import com.newx.common.frameworks.support.mvvm.NxBindingFragment;
import com.newx.camera.R;
import com.newx.ui.fragmentation.ISupportFragment;

/**
 * Created by newx on 18-6-18.
 */

@Route(path = RP.FragmentCameraRoot)
public class FragmentCameraRoot extends NxBindingFragment<FragmentCameraRootBinding> {
    @Override
    public int initContentView() {
        return R.layout.fragment_camera_root;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        mBinding.buttonGallery.setOnClickListener(v -> {
            ISupportFragment fragment = (ISupportFragment) ARouter.getInstance()
                    .build(RP.FragmentGallery)
                    .navigation();

            start(fragment);
        });

        mBinding.buttonCamera.setOnClickListener(v -> {
            ISupportFragment fragment = (ISupportFragment) ARouter.getInstance()
                    .build(RP.FragmentCamera)
                    .navigation();

            start(fragment);
        });
    }
}
