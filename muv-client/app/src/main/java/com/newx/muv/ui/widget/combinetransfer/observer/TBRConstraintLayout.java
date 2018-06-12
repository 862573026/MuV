package com.newx.muv.ui.widget.combinetransfer.observer;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.newx.muv.ui.widget.combinetransfer.base.TransferBindObserver;

/**
 * Created by xuzhijian on 2018/4/12 0012.
 * ConstraintLayout 联动变化
 */

public class TBRConstraintLayout extends ConstraintLayout implements TransferBindObserver {

    private TBReceiveDelegate mObserverDelegate = new TBReceiveDelegate(this);


    public TBRConstraintLayout(Context context) {
        super(context);
    }

    public TBRConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TBRConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
