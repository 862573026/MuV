package com.newx.base.frameworks.support.activity;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * Created by xuzhijian on 2018/4/10 0010.
 * 所有Activity的基类
 */

abstract class BaseActivity extends RxAppCompatActivity {

    protected View containerView;

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
    }

}
