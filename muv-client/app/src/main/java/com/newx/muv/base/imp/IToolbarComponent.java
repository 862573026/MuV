package com.newx.muv.base.imp;


import android.support.v7.widget.Toolbar;

import com.newx.base.widget.ToolbarHelper;
import com.newx.muv.utils.ToolbarManager;

/**
 * Created by xuzhijian on 2018/5/22 0022.
 * 有Toolbar的Activity
 */

public interface IToolbarComponent {

    void initToolbar();

    boolean showToolbar();
}
