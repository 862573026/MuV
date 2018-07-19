package com.newx.common.frameworks.support.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.newx.common.frameworks.support.activity.NxActivity;
import com.newx.utils.def.INVALID;
import com.newx.utils.logger.NXLog;

/**
 * Created by xuzhijian on 2018/6/11 0011.
 * 实现DataBing
 */

public abstract class NxBindingActivity<V extends ViewDataBinding> extends NxActivity {

    protected V mBinding;
    private int mLayoutId = INVALID.ID;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    private void initDataBinding() {
        mLayoutId = initContentView();

        if (mLayoutId == INVALID.ID) {
            throw new RuntimeException(String.format("请在 %1$s 的 initContentView 方法设置LayoutId", this.getClass().getName()));
        } else {
            mBinding = DataBindingUtil.inflate(getLayoutInflater(), mLayoutId, mToolbarHelper.getContainerView(), true); //需要attach
            containerView = mBinding.getRoot();
            initContainer();
            if (mBinding == null) {
                NXLog.e(String.format( "%1$s 的 initContentView 方法里设置的界面不支持DataBinding，考虑加入<layout>，或者自己设置contentView",
                        this.getClass().getName()));
            }
        }
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    @LayoutRes
    public abstract int initContentView();
}
