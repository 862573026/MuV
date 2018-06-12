package com.newx.muv.view;

import android.os.Bundle;
import android.widget.Toast;

import com.newx.base.ui.swipeback.SwipeBackActivity;
import com.newx.muv.R;

/**
 * Created by xuzhijian on 2018/6/11 0011.
 */

public class TestActivity extends SwipeBackActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // 给其下Fragment的默认背景 (默认使用Fragment根布局的background属性,如若没有则使用Theme的windowBackground属性)
        setDefaultFragmentBackground(android.R.color.white);

        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container,CycleFragment.newInstance(0));
        } else {
            Toast.makeText(TestActivity.this, "啊哦,app被强杀喽~", Toast.LENGTH_LONG).show();
        }
    }

}

