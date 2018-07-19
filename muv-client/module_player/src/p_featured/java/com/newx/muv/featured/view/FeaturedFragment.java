package com.newx.muv.featured.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.common.frameworks.widget.bar.ImmersionBar;
import com.newx.muv.featured.vm.FeatureVM;
import com.newx.player.R;
import com.newx.player.databinding.FragmentFeaturedBinding;


/**
 * Created by newx on 18-4-8.
 */
@Route(path = RP.FeaturedFragment)
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
    }

    @Override
    protected void setImmersionBar() {
        super.setImmersionBar();
        ImmersionBar.with(this)
                .transparentStatusBar()
                .init();
    }
}
