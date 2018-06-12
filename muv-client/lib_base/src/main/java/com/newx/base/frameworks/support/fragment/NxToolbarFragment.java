package com.newx.base.frameworks.support.fragment;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.newx.base.widget.ToolbarHelper;

/**
 * Created by xuzhijian on 2018/6/11 0011.
 * Toolbar封装
 */

class NxToolbarFragment extends NxImmersionBarFragment {

    //Toolbar封装
    protected FragmentActivity _mActivity;
    protected ToolbarHelper _mToolbarHelper;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        _mActivity = mSupportFragmentDelegate.getActivity();
    }


    public boolean showToolbar() {
        return false;
    }

    public void initToolbar() {
    }

    public void setToolbarVisible(boolean visible) {
        if (showToolbar()) {
            if(_mToolbarHelper.getToolbar() != null){
                if (visible) {
                    _mToolbarHelper.getToolbar().setVisibility(View.VISIBLE);
                } else {
                    _mToolbarHelper.getToolbar().setVisibility(View.GONE);
                }
            }
        }
    }
}
