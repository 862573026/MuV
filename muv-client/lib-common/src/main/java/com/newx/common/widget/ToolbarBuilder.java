package com.newx.common.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.newx.common.R;
import com.newx.common.proxy.ResourceProxy;
import com.newx.utils.def.INVALID;

/**
 * Created by newx on 18-5-27.
 * Toolbar配置
 */

public class ToolbarBuilder {

    private ToolbarBuilder() {
    }

    public ToolbarBuilder(Context context) {
        mContext = context;
    }

    public static ToolbarBuilder newBuilder(Context context) {
        return new ToolbarBuilder(context);
    }

    private Context mContext;
    private int mTitleRes = INVALID.ID;
    private int mLeftImageRes = INVALID.ID;
    private int mLeftTextRes = INVALID.ID;
    private View.OnClickListener mLeftClickListener;
    private int mToolbarHeight = (int) ResourceProxy.getDimension(R.dimen.toolbar_height);

    public ToolbarBuilder title(@StringRes int titleRes) {
        mTitleRes = titleRes;
        return this;
    }

    public ToolbarBuilder leftImage(@DrawableRes int leftImageRes) {
        mLeftImageRes = leftImageRes;
        return this;
    }

    public ToolbarBuilder leftText(@StringRes int leftTextRes) {
        mLeftTextRes = leftTextRes;
        return this;
    }

    public ToolbarBuilder toolbarHeight(int toolbarHeight) {
        mToolbarHeight = toolbarHeight;
        return this;
    }

    public ToolbarBuilder leftClickListener(View.OnClickListener clickListener) {
        mLeftClickListener = clickListener;
        return this;
    }

    public TitleBar build() {
        TitleBar toolbar = new TitleBar(mContext);

        toolbar.setId(R.id.toolbar); //默认id

        if (mTitleRes != INVALID.ID) {
            toolbar.setTitle(mTitleRes);
        }
        if(mLeftImageRes != INVALID.ID){
            toolbar.setLeftImageResource(mLeftImageRes);
        }
        if(mLeftTextRes!= INVALID.ID){
            toolbar.setLeftText(mLeftTextRes);
        }

        toolbar.setLeftClickListener(mLeftClickListener);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, mToolbarHeight);
        params.gravity = Gravity.TOP;
        toolbar.setHeight(mToolbarHeight);
        toolbar.setLayoutParams(params);

        return toolbar;
    }


}
