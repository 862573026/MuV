package com.newx.common.frameworks.support.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.newx.ui.swipeback.ISwipeBackActivity;
import com.newx.ui.swipeback.SwipeBackActivityDelegate;
import com.newx.ui.swipeback.SwipeBackLayout;

/**
 * Created by xuzhijian on 2018/6/11 0011.
 * 实现 右滑返回功能
 */

abstract class NxSwipeBackActivity extends NxImmersionBarActivity
 implements ISwipeBackActivity {
    final SwipeBackActivityDelegate mSwipeBackActivityDelegate = new SwipeBackActivityDelegate(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeBackActivityDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mSwipeBackActivityDelegate.onPostCreate(savedInstanceState);
    }

    //
    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mSwipeBackActivityDelegate.getSwipeBackLayout();
    }

    /**
     * 是否可滑动
     * @param enable
     */
    @Override
    public void setSwipeBackEnable(boolean enable) {
        mSwipeBackActivityDelegate.setSwipeBackEnable(enable);
    }

    @Override
    public void setEdgeLevel(SwipeBackLayout.EdgeLevel edgeLevel) {
        mSwipeBackActivityDelegate.setEdgeLevel(edgeLevel);
    }

    @Override
    public void setEdgeLevel(int widthPixel) {
        mSwipeBackActivityDelegate.setEdgeLevel(widthPixel);
    }

    /**
     * 限制SwipeBack的条件,默认栈内Fragment数 <= 1时 , 优先滑动退出Activity , 而不是Fragment
     *
     * @return true: Activity优先滑动退出;  false: Fragment优先滑动退出
     */
    @Override
    public boolean swipeBackPriority() {
        return mSwipeBackActivityDelegate.swipeBackPriority();
    }
}
