package com.newx.common.helper.smartrefreshlayout;

import android.databinding.BindingAdapter;

import com.newx.common.command.BindingCommand;
import com.newx.ui.refreshlayout.SmartRefreshLayout;


/**
 * Created by xuzhijian on 2018/4/17 0017.
 */

public class SmartRefreshLayoutAdapter {

    @BindingAdapter("onRefreshCommand")
    public static void onRefreshCommand(SmartRefreshLayout swipeRefreshLayout, final BindingCommand onRefreshCommand) {
        swipeRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (onRefreshCommand != null) {
                onRefreshCommand.execute();
            }
        });
    }

    @BindingAdapter("onLoadMoreCommand")
    public static void onLoadMoreCommand(SmartRefreshLayout swipeRefreshLayout, final BindingCommand onLoadMoreCommand) {
        swipeRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (onLoadMoreCommand != null) {
                onLoadMoreCommand.execute();
            }
        });
    }
}
