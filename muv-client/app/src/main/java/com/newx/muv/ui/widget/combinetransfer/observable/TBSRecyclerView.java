package com.newx.muv.ui.widget.combinetransfer.observable;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;

import com.newx.base.frameworks.util.log.NXLog;
import com.newx.entity.def.INVALID;
import com.newx.muv.base.debug.DEBUG;
import com.newx.muv.ui.widget.combinetransfer.base.BindDefine;
import com.newx.muv.utils.rx.rxbus.RxBus;


/**
 * Created by xuzhijian on 2018/4/11 0011.
 * 可监听滑动距离的 RecyclerView
 */

public class TBSRecyclerView extends RecyclerView {

    private String EVENT_TAG = INVALID.TAG;

    private int MAX_Y_OFFSET = BindDefine.DEFAULT_MAX_Y_OFFSET;

    private final SparseIntArray mRecyclerItemHeight = new SparseIntArray();

    public TBSRecyclerView(Context context) {
        super(context);
        init();
    }

    public TBSRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TBSRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        addOnScrollListener(mScrollListener);
        EVENT_TAG = INVALID.TAG; //TAG 也可以在XML注册
    }


    OnScrollListener mScrollListener = new OnScrollListener() {
//        private int offsetY = 0; //需要监听插入、删除、移动

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            //GridLayoutManager通用吗 -> 测试没发现问题
            if (!INVALID.TAG.equals(EVENT_TAG)) { //TAG的重复情况
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                View v = recyclerView.getLayoutManager().getChildAt(0);
                if (v != null) {
                    int scrollY = -v.getTop();
                    mRecyclerItemHeight.put(firstVisibleItem, v.getHeight());
                    for (int i = 0; i < firstVisibleItem; ++i) {
                        scrollY += mRecyclerItemHeight.get(i);
                    }
                    RxBus.getInstance().postSticky(EVENT_TAG, scrollY);
                }
            } else {
                NXLog.t(DEBUG.TAG.SYSTEM_LOG).e(DEBUG.MESSAGE.MESSAGE_BIND_OBSERVABLE_IS_NULL);
            }
        }
    };


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
