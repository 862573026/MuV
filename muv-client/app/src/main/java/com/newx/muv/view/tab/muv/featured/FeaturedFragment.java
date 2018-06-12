package com.newx.muv.view.tab.muv.featured;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.widget.toast.ToastUtil;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentFeaturedBinding;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.FeatureVM;

/**
 * Created by newx on 18-4-8.
 */
@Route(path = FRAGMENT.FeaturedFragment)
public class FeaturedFragment extends NxMvvMFragment<FragmentFeaturedBinding, FeatureVM> {

    @Override
    public int initContentView() {
        return R.layout.fragment_featured;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public FeatureVM initVM() {
        return null;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        ToastUtil.showShort("懒加载调用");
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
