package com.newx.muv.ui.widget.combinetransfer.observable;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

import com.newx.base.frameworks.util.log.NXLog;
import com.newx.entity.def.INVALID;
import com.newx.muv.base.debug.DEBUG;
import com.newx.muv.ui.widget.combinetransfer.base.BindDefine;
import com.newx.muv.utils.rx.rxbus.RxBus;

/**
 * Created by xuzhijian on 2018/4/25 0025.
 * 可监听滑动距离的 NestedScrollView
 */

public class TBSNestedScrollView extends NestedScrollView {

    private String EVENT_TAG = INVALID.TAG;

    private int MAX_Y_OFFSET = BindDefine.DEFAULT_MAX_Y_OFFSET;

    public TBSNestedScrollView(Context context) {
        super(context);
        init();
    }

    public TBSNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TBSNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        EVENT_TAG = INVALID.TAG;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        NXLog.e("Scrolled:" + getScrollY());
        if (!INVALID.TAG.equals(EVENT_TAG)) { //TAG的重复情况
            RxBus.getInstance().postSticky(EVENT_TAG, getScrollY());
        } else {
            NXLog.t(DEBUG.TAG.SYSTEM_LOG).e(DEBUG.MESSAGE.MESSAGE_BIND_OBSERVABLE_IS_NULL);
        }
    }

    public int getMaxYOffset() {
        return MAX_Y_OFFSET;
    }

    public void setMaxYOffset(int offset) {
        this.MAX_Y_OFFSET = offset;
    }

    public void setEventTag(String tag) {
        this.EVENT_TAG = tag;
    }
}
