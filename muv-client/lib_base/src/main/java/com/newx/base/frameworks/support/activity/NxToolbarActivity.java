package com.newx.base.frameworks.support.activity;

import android.os.Bundle;

import com.newx.base.R;
import com.newx.base.frameworks.imp.IToolbarComponent;
import com.newx.base.frameworks.support.activity.BaseActivity;
import com.newx.base.widget.ToolbarBuilder;
import com.newx.base.widget.ToolbarHelper;

/**
 * Created by xuzhijian on 2018/6/11 0011.
 * Toolbar封装
 */

abstract class NxToolbarActivity extends BaseActivity implements IToolbarComponent {

    protected ToolbarHelper mToolbarHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbar();

        mToolbarHelper = ToolbarHelper.with(this);
        mToolbarHelper.setToolbar(ToolbarBuilder
                .newBuilder(this)
                .build());

        if (showToolbar()) {
            mToolbarHelper.getToolbar()
                    .setLeftImageResource(R.drawable.icon_text_back_white);
            mToolbarHelper.getToolbar()
                    .setLeftClickListener(v -> onBackPressed());
        }

        mToolbarHelper.getToolbar().setLeftClickListener(v -> onBackPressed());
    }

    public void initToolbar() {

    }

    public boolean showToolbar() {
        return false;
    }
}
