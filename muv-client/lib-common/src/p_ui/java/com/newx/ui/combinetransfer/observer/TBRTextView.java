package com.newx.ui.combinetransfer.observer;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.newx.ui.combinetransfer.base.TransferBindObserver;


/**
 * Created by xuzhijian on 2018/4/11 0011.
 * 联动变化的TextView
 */

public class TBRTextView extends AppCompatTextView implements TransferBindObserver {

    private TBReceiveDelegate mObserverDelegate = new TBReceiveDelegate(this);

    public TBRTextView(Context context) {
        super(context);
    }

    public TBRTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TBRTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setProperty(BindProperty property) {
        mObserverDelegate.setProperty(property);
    }

    /**
     * 必须设置才能监听
     *
     * @param tag
     */
    @Override
    public void setEventTag(String tag) {
        mObserverDelegate.setEventTag(tag);
    }
}
