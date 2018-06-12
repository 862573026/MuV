package com.newx.muv.ui.widget.toolbar;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.newx.muv.R;
import com.newx.muv.databinding.StandardToolbarBinding;
import com.newx.muv.ui.widget.toolbar.imp.IToolbar;


/**
 * Created by newx on 18-5-25.
 * Toolbar封装
 */

public class StandardToolbar extends Toolbar implements IToolbar{

    private StandardToolbarBinding mToolbarBinding;

    public StandardToolbar(Context context) {
        super(context);
        initView(context);
    }

    public StandardToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public StandardToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mToolbarBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.standard_toolbar, this, false);
    }

    public TextView getCenterTitle() {
        return mToolbarBinding.tvCenter;
    }

    public void setCenterTitle(@StringRes int titleRes) {
        mToolbarBinding.tvCenter.setText(titleRes);
    }

    public void setLeftIcon(Drawable left) {
        mToolbarBinding.tvLeftDes.setCompoundDrawables(left,
                null, null, null);
    }

    public void setLeftDes(@StringRes int titleRes) {
        mToolbarBinding.tvLeftDes.setText(titleRes);
    }

    public void setRightDes(@StringRes int titleRes) {
        mToolbarBinding.tvRightDes.setText(titleRes);
    }
}
