package com.newx.muv.view.tab.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.muv.BR;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentMeBinding;
import com.newx.muv.view.TestActivity;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.MeVM;

/**
 * Created by xuzhijian on 2018/4/8 0008.
 */
@Route(path = FRAGMENT.MeFragment)
public class MeFragment extends NxMvvMFragment<FragmentMeBinding, MeVM> {

    @Override
    public int initContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public int initVariableId() {
        return BR.meVM;
    }

    @Override
    public MeVM initVM() {
        return new MeVM();
    }

    @Override
    public int initContainerId() {
        return R.id.layout_me;
    }

    @Override
    public void initToolbar() {
        _mToolbarHelper.getToolbar().setLeftVisible(false);
        _mToolbarHelper.getToolbar().setTitle(R.string.me_text);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mBinding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JumpHelper.fragment(FRAGMENT.TestFragmentA)
//                        .navigation();
                startActivity(new Intent(getActivity(), TestActivity.class));
            }
        });
    }

    @Override
    public boolean showToolbar() {
        return true;
    }

}
