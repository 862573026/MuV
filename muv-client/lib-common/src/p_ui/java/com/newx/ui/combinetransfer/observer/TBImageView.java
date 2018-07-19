package com.newx.ui.combinetransfer.observer;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.newx.ui.combinetransfer.base.TransferBindObserver;


/**
 * Created by xuzhijian on 2018/4/12 0012.
 */

public class TBImageView extends AppCompatImageView implements TransferBindObserver {

    private TBReceiveDelegate mObserverDelegate = new TBReceiveDelegate(this);

    public TBImageView(Context context) {
        super(context);
    }

    public TBImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TBImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setEventTag(String tag) {
        mObserverDelegate.setEventTag(tag);
    }

    @Override
    public void setProperty(BindProperty property) {
        mObserverDelegate.setProperty(property);
    }
}
