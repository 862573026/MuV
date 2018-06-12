package com.newx.base.frameworks.support.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newx.base.R;
import com.newx.base.frameworks.imp.IToolbarComponent;
import com.newx.base.frameworks.support.fragment.NxFragment;
import com.newx.base.frameworks.util.log.NXLog;
import com.newx.base.frameworks.widget.bar.ImmersionBar;
import com.newx.base.ui.fragmentation.ISupportFragment;
import com.newx.base.widget.ToolbarBuilder;
import com.newx.base.widget.ToolbarHelper;
import com.newx.entity.def.INVALID;


/**
 * Created by xuzhijian on 2018/5/4 0004.
 */

public abstract class NxBindingFragment<V extends ViewDataBinding> extends NxFragment {

    protected V mBinding;

    private int mLayoutId = INVALID.ID;

    public int containerId = INVALID.ID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutId = initContentView();
        setToolbar(container);


        if (mLayoutId == INVALID.ID) {
            throw new RuntimeException(String.format("请在 %1$s 的 initContentView 方法设置LayoutId", this.getClass().getName()));
        } else {
            mBinding = DataBindingUtil.inflate(inflater, mLayoutId, container, false);
            if (mBinding != null) {
                this.context = getContext();
                this.inflater = inflater;
                this.container = container;
                this.contentView = mBinding.getRoot();
                containerId = initContainerId();
            } else {
                NXLog.e(String.format("%1$s 的 initContentView 方法里设置的界面不支持DataBinding，考虑加入<layout>，或者自己设置contentView",
                        this.getClass().getName()));
            }
        }


        onCreateView(savedInstanceState);
        return contentView;

    }


    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    @LayoutRes
    public abstract int initContentView();



    //需要在 DataBindingUtil.inflate 之前
    public void setToolbar(ViewGroup container){
        _mToolbarHelper = ToolbarHelper.with(this);

        if (showToolbar() && (_mActivity instanceof IToolbarComponent)) {

            if (_mActivity instanceof IToolbarComponent) {
                _mToolbarHelper.setToolbar(
                        ToolbarBuilder.newBuilder(getContext())
                                .leftImage(R.drawable.icon_text_back_white)
                                .leftClickListener(v -> _mActivity.onBackPressed())
                                .build(),
                        container, (ViewGroup) container.getParent());

                _mToolbarHelper.showToolbar(true);
                initToolbar();
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            View titleBar = view.findViewById(setTitleBar());
            if (titleBar != null) {
                ImmersionBar.setTitleBar(_mActivity, titleBar);
            }
            View statusBarView = view.findViewById(setStatusBarView());
            if (statusBarView != null) {
                ImmersionBar.setStatusBarView(_mActivity, statusBarView);
            }
        }
    }

    protected int setTitleBar() {
        return 0;
    }

    protected int setStatusBarView() {
        return 0;
    }

    public boolean onBackPressed() {
        return false;
    }

//    @Override
//    public InjectConfig injectConfig() {
//        return null;
//    }

    public int initContainerId() {
        if (containerId != INVALID.ID) {
            return containerId;
        }

        if (container != null && container.getId() == INVALID.ID) {
            container.setId(R.id.container); //设置 containerView的Id
        }
        return container.getId();
    }


    /**
     * 设置默认containerId
     * @param toFragment
     */
    public void loadRootFragment(ISupportFragment toFragment) {
        mSupportFragmentDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadRootFragment(ISupportFragment toFragment, boolean addToBackStack, boolean allowAnim) {
        mSupportFragmentDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnim);
    }

}
