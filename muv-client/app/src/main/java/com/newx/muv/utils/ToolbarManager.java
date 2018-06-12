package com.newx.muv.utils;


import com.newx.base.widget.TitleBar;


/**
 * Created by newx on 18-5-24.
 * Toolbar管理
 */

public class ToolbarManager {

    private TitleBar mToolbar;
    private boolean mShowToolbar = true;
    private boolean mNeedBack = true;


    public ToolbarManager(TitleBar toolbar) {
        mToolbar = toolbar;
    }

    public boolean isShowToolbar() {
        return mShowToolbar;
    }

    public void setShowToolbar(boolean showToolbar) {
        mShowToolbar = showToolbar;
    }

    public boolean isNeedBack() {
        return mNeedBack;
    }

    public void setNeedBack(boolean needBack) {
        mNeedBack = needBack;
    }

    public TitleBar getToolbar() {
        return mToolbar;
    }



    public static class Builder {

    }

    public static interface OnToolbarChangeListener{
        void change();
    }
}
