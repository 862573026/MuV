package com.newx.user.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.user.BR;
import com.newx.user.R;
import com.newx.user.databinding.FragmentMeBinding;
import com.newx.user.vm.MeVM;


/**
 * Created by xuzhijian on 2018/4/8 0008.
 */
@Route(path = RP.MeFragment)
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
        mBinding.btnTest.setOnClickListener(v -> {
//                JumpHelper.fragment(FRAGMENT.TestFragmentA)
//                        .navigation();
//                startActivity(new Intent(getActivity(), TestActivity.class));
        });
    }

    @Override
    public boolean showToolbar() {
        return true;
    }

}
