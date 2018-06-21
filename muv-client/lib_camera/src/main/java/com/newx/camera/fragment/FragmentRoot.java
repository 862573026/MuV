package com.newx.camera.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.base.frameworks.support.mvvm.NxBindingFragment;
import com.newx.base.ui.fragmentation.ISupportFragment;
import com.newx.camera.R;
import com.newx.camera.databinding.FragmentRootBinding;
import com.newx.muv.view.route.FRAGMENT;

/**
 * Created by newx on 18-6-18.
 */

@Route(path = FRAGMENT.FragmentRoot)
public class FragmentRoot extends NxBindingFragment<FragmentRootBinding> {
    @Override
    public int initContentView() {
        return R.layout.fragment_root;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        mBinding.buttonGallery.setOnClickListener(v -> {
            ISupportFragment fragment = (ISupportFragment) ARouter.getInstance()
                    .build(FRAGMENT.FragmentGallery)
                    .navigation();

            start(fragment);
        });

        mBinding.buttonCamera.setOnClickListener(v -> {
            ISupportFragment fragment = (ISupportFragment) ARouter.getInstance()
                    .build(FRAGMENT.FragmentCamera)
                    .navigation();

            start(fragment);        });
    }
}
