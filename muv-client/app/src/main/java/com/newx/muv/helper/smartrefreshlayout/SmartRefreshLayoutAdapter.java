package com.newx.muv.helper.smartrefreshlayout;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;

import com.newx.base.ui.refreshlayout.SmartRefreshLayout;
import com.newx.base.ui.refreshlayout.api.RefreshLayout;
import com.newx.base.ui.refreshlayout.listener.OnLoadMoreListener;
import com.newx.base.ui.refreshlayout.listener.OnRefreshListener;
import com.newx.muv.base.databinding.command.BindingCommand;

/**
 * Created by xuzhijian on 2018/4/17 0017.
 */

public class SmartRefreshLayoutAdapter {

    @BindingAdapter("onRefreshCommand")
    public static void onRefreshCommand(SmartRefreshLayout swipeRefreshLayout, final BindingCommand onRefreshCommand) {
        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute();
                }
            }
        });
    }

    @BindingAdapter("onLoadMoreCommand")
    public static void onLoadMoreCommand(SmartRefreshLayout swipeRefreshLayout, final BindingCommand onLoadMoreCommand) {
        swipeRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (onLoadMoreCommand != null) {
                    onLoadMoreCommand.execute();
                }
            }
        });
    }
}
