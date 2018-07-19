package com.newx.common.frameworks.support.mvvm;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


/**
 * Created by xuzhijian on 2018/5/4 0004.
 * MVVM DataBinding模式的Fragment基类
 */

public abstract class NxMvvMFragment<V extends ViewDataBinding, VM extends BaseViewModel>
        extends NxBindingFragment<V> implements IBaseVMComponent {

    protected VM mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();

        if (mBinding != null) {
            mBinding.setVariable(initVariableId(), mViewModel = initVM());
        }

        initViewObservable();//需要在 onCreateViewLazy 之后
        initData();

        if (mViewModel != null) {
            mViewModel.onCreate();
        }
    }

    //刷新布局
    public void refreshLayout() {
        if (mViewModel != null) {
            mBinding.setVariable(initVariableId(), mViewModel);
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

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void initParam() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (mViewModel != null) {
            mViewModel.lazyLoad();
        }
    }

    public abstract VM initVM();

}
