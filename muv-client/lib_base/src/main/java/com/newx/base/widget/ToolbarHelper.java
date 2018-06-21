package com.newx.base.widget;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;


import com.newx.base.R;
import com.newx.base.def.DESCRIPTION;
import com.newx.base.def.TAG;

import java.lang.ref.WeakReference;


/**
 * Created by newx on 18-5-27.
 * Toolbar 帮助类
 */

public class ToolbarHelper {

    private Activity mActivity;
    private Window mWindow;
    private ViewGroup mDecorView;
    private ViewGroup mContentView;

    private ViewGroup mContainerView;

    private String mActivityName;
    private String mFragmentName;
    private String mToolbarName;

    protected ToolbarBuilder mToolbarBuilder;
    private TitleBar mToolbar;

    /**
     * 在Activit里初始化
     * Instantiates a new Immersion bar.
     *
     * @param activity the activity
     */
    private ToolbarHelper(Activity activity) {
        WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);
        mActivity = activityWeakReference.get();
        mWindow = mActivity.getWindow();
        mActivityName = activity.getClass().getName();
        mToolbarName = mActivityName;
        initParams();
    }

    /**
     * 在Fragment里初始化
     * Instantiates a new Immersion bar.
     *
     * @param fragment the fragment
     */
    private ToolbarHelper(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }

    private ToolbarHelper(Activity activity, Fragment fragment) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity不能为空!!!");
        }
        WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);
        WeakReference<Fragment> fragmentWeakReference = new WeakReference<>(fragment);
        mActivity = activityWeakReference.get();
        mWindow = mActivity.getWindow();
        mActivityName = mActivity.getClass().getName();
        mFragmentName = mActivityName + "_AND_" + fragmentWeakReference.get().getClass().getName();
        mToolbarName = mFragmentName;
        initParams();
    }

    public static ToolbarHelper with(@NonNull Activity activity) {
        if (activity == null)
            throw new IllegalArgumentException("Activity不能为null");
        return new ToolbarHelper(activity);
    }

    public static ToolbarHelper with(@NonNull Fragment fragment) {
        if (fragment == null)
            throw new IllegalArgumentException("Fragment不能为null");
        return new ToolbarHelper(fragment);
    }


    /**
     * 初始化沉浸式默认参数
     * Init params.
     */
    private void initParams() {
        mDecorView = (ViewGroup) mWindow.getDecorView();
        mContentView = (ViewGroup) mDecorView.findViewById(android.R.id.content);
    }

    public void showToolbar(boolean flag) {
        if (flag) {
            mContainerView.setPadding(0, mToolbar.getToolbarHeight(), 0, 0);
        } else {
            mContainerView.setPadding(0, 0, 0, 0);
        }
    }

    public TitleBar getToolbar() {
        return mToolbar;
    }

    public void setToolbar(TitleBar toolbar) {
        mToolbar = toolbar;

        mContentView.removeAllViews(); //移除之前的

        mContentView.addView(mToolbar);
        mContainerView = new FrameLayout(mActivity);
        mContainerView.setId(R.id.container);
        mContainerView.setTag(TAG.VIEW_CONTAINER);
        mContainerView.setContentDescription(DESCRIPTION.VIEW_CONTAINER);
        mContentView.addView(mContainerView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setToolbar(@NonNull TitleBar toolbar,
                           @NonNull ViewGroup containerView,
                           @NonNull ViewGroup contentView) {
        mToolbar = toolbar;
        mContentView.addView(mToolbar);
        mContainerView = containerView;
        mContentView = contentView;
    }

    public ViewGroup getDecorView() {
        return mDecorView;
    }

    public ViewGroup getContentView() {
        return mContentView;
    }

    public ViewGroup getContainerView() {
        return mContainerView;
    }
}
