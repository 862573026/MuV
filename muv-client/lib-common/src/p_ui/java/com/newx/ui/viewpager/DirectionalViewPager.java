package com.newx.ui.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.newx.common.R;

/**
 * Created by newx on 18-4-24.
 */

public class DirectionalViewPager extends SViewPager {

    public static final int SCROLL_MODE_HORIZONTAL = 0;
    public static final int SCROLL_MODE_VERTICAL = 1;

    private int orientationStyle;

    private float touchX;
    private float touchY;
    private float ratio = -1f;


    public DirectionalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.DirectionalViewPager);
        orientationStyle = t.getInt(R.styleable.DirectionalViewPager_orientation, 0);
        t.recycle();
        setScrollModeHorizontal(orientationStyle);
    }

    public void setScrollModeHorizontal(int mode) {
        if (mode == SCROLL_MODE_VERTICAL) {
            setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
            setPageTransformer(true, new VerticalPageTransformer());
        }
        orientationStyle = mode;
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View view, float position) {
            if (position <= 1) {
                view.setAlpha(1);
                view.setTranslationX(view.getWidth() * -position);
                //set Y position to swipe in from top
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);

            } else {
                view.setAlpha(0);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return super.onInterceptTouchEvent(customTouchEvent(MotionEvent.obtain(event)));
    }

    private MotionEvent customTouchEvent(MotionEvent ev) {
        if (orientationStyle == SCROLL_MODE_VERTICAL) {
            if (ratio == -1) {
                ratio = (float) getWidth() / getHeight();
            }
            touchX = ev.getX();
            touchY = ev.getY();
            ev.setLocation(touchY * ratio, touchX / ratio);
        }
        return ev;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(customTouchEvent(ev));
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        //下面这句话的作用 告诉父view，我的单击事件我自行处理，不要阻碍我。
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

}
