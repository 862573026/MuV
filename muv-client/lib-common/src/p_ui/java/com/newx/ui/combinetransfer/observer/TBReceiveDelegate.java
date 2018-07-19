package com.newx.ui.combinetransfer.observer;

import android.view.View;

import com.newx.common.utils.RxBus;
import com.newx.ui.combinetransfer.proxy.TransferProxy;
import com.newx.utils.def.INVALID;


/**
 * Created by xuzhijian on 2018/4/25 0025.
 * 监听者代理 -> 因为基本上都是一样的
 */

public class TBReceiveDelegate {

    private String EVENT_TAG = INVALID.TAG;

    private View mView;

    private BindProperty mProperty = BindProperty.newBuilder().build(); //初始化

    public TBReceiveDelegate(View view) {
        mView = view;
    }

    public void setProperty(BindProperty property) {
        TransferProxy.initView(mView, property);
        this.mProperty = property;
    }

    public void setEventTag(String tag) {
        this.EVENT_TAG = tag;

        if (!INVALID.TAG.equals(EVENT_TAG)) {
            RxBus.getInstance().register(EVENT_TAG, Integer.class, offset -> {
                TransferProxy.transferAlpha(mView, mProperty, offset);
                TransferProxy.transferScale(mView, mProperty, offset);
                TransferProxy.isVisible(mView, mProperty);
            });
        }
    }
}
