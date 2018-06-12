package com.newx.base.frameworks.support.mvvm;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.base.frameworks.util.log.NXLog;

/**
 * Created by xuzhijian on 2018/4/13 0013.
 */

public abstract class NxMvvMActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends NxBindingActivity<V>
        implements IBaseVMComponent {

    @Nullable
    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initParam();

        if (mBinding != null) {
            mBinding.setVariable(initVariableId(), mViewModel = initVM());
        } else {
            NXLog.e(
                    this.getClass().getName()+" 的 initContentView 方法里设置的界面不支持DataBinding，考虑加入<layout>，或者自己设置contentView");
        }

        initData();

        initViewObservable();

        if (mViewModel != null) {
            mViewModel.onCreate();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.onDestroy();
        }
        mViewModel = null;
    }


    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    @Override
    public void initParam() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public abstract VM initVM();
}
