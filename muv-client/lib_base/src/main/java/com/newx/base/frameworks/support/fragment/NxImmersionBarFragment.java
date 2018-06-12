package com.newx.base.frameworks.support.fragment;

import com.newx.base.frameworks.widget.bar.ImmersionBar;

/**
 * Created by xuzhijian on 2018/6/11 0011.
 * 沉浸式状态栏
 */

class NxImmersionBarFragment extends NxSupportFragment {

    //沉浸式状态栏
    protected ImmersionBar mImmersionBar;

    @Override
    public void onSupportVisible() {
        //如果要在Fragment单独使用沉浸式，请在onSupportVisible实现沉浸式

        if (isImmersionBarEnabled()) {
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.navigationBarWithKitkatEnable(false).init();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    protected boolean isImmersionBarEnabled() {
        return false;
    }

}
