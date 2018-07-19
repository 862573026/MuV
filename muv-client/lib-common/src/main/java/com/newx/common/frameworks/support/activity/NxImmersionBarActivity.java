package com.newx.common.frameworks.support.activity;

import android.os.Bundle;

import com.newx.common.frameworks.widget.bar.ImmersionBar;

/**
 * Created by xuzhijian on 2018/6/11 0011.
 * 实现沉浸式
 */

abstract class NxImmersionBarActivity extends NxToolbarActivity {

    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setImmersionBar();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    public void setImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true)
                .navigationBarWithKitkatEnable(false)
                .init();
    }
}
