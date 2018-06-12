package com.newx.base.frameworks.support.activity;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.newx.base.R;
import com.newx.base.def.CONSTANT;
import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.base.frameworks.support.fragment.NxFragment;
import com.newx.base.ui.fragmentation.ExtraTransaction;
import com.newx.base.ui.fragmentation.ISupportActivity;
import com.newx.base.ui.fragmentation.ISupportFragment;
import com.newx.base.ui.fragmentation.SupportActivityDelegate;
import com.newx.base.ui.fragmentation.SupportFragment;
import com.newx.base.ui.fragmentation.SupportHelper;
import com.newx.base.ui.fragmentation.anim.FragmentAnimator;
import com.newx.entity.def.INVALID;


/**
 * Created by xuzhijian on 2018/6/11 0011.
 * 实现FragmentAction
 */

abstract class NxFragmentActivity extends NxImmersionBarActivity
        implements ISupportActivity {

    final SupportActivityDelegate mSupportActivityDelegate = new SupportActivityDelegate(this);
    public int containerId = INVALID.ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSupportActivityDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSupportActivityDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mSupportActivityDelegate.onDestroy();
        super.onDestroy();
    }

    /**
     * Note： return mSupportActivityDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mSupportActivityDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    /**
     * 不建议复写该方法,请使用 {@link #onBackPressedSupport} 代替
     */
    @Override
    final public void onBackPressed() {
        mSupportActivityDelegate.onBackPressed();
    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    @Override
    public void onBackPressedSupport() {
        mSupportActivityDelegate.onBackPressedSupport();
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mSupportActivityDelegate.getFragmentAnimator();
    }

    /**
     * Set all fragments animation.
     * 设置Fragment内的全局动画
     */
    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mSupportActivityDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mSupportActivityDelegate.onCreateFragmentAnimator();
    }

    /****************************************以下为可选方法(Optional methods)******************************************************/

    /**
     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
     *
     * @param toFragment 目标Fragment
     */
    public void loadRootFragment(@NonNull ISupportFragment toFragment) {
        mSupportActivityDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadRootFragment(ISupportFragment toFragment, boolean addToBackStack, boolean allowAnimation) {
        mSupportActivityDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnimation);
    }

    /**
     * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
     */
    public void loadMultipleRootFragment(int showPosition, ISupportFragment... toFragments) {
        mSupportActivityDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    /**
     * show一个Fragment,hide其他同栈所有Fragment
     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
     * <p>
     * 建议使用更明确的{@link #showHideFragment(ISupportFragment, ISupportFragment)}
     *
     * @param showFragment 需要show的Fragment
     */
    public void showHideFragment(ISupportFragment showFragment) {
        mSupportActivityDelegate.showHideFragment(showFragment);
    }

    /**
     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
     */
    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
        mSupportActivityDelegate.showHideFragment(showFragment, hideFragment);
    }

    /**
     * It is recommended to use {@link SupportFragment#start(ISupportFragment)}.
     */
    public void start(ISupportFragment toFragment) {
        mSupportActivityDelegate.start(toFragment);
    }

    /**
     * It is recommended to use {@link SupportFragment#start(ISupportFragment, int)}.
     *
     * @param launchMode Similar to Activity's LaunchMode.
     */
    public void start(ISupportFragment toFragment, @ISupportFragment.LaunchMode int launchMode) {
        mSupportActivityDelegate.start(toFragment, launchMode);
    }

    /**
     * It is recommended to use {@link SupportFragment#startForResult(ISupportFragment, int)}.
     * Launch an fragment for which you would like a result when it poped.
     */
    public void startForResult(ISupportFragment toFragment, int requestCode) {
        mSupportActivityDelegate.startForResult(toFragment, requestCode);
    }

    /**
     * It is recommended to use {@link SupportFragment#startWithPop(ISupportFragment)}.
     * Launch a fragment while poping self.
     */
    public void startWithPop(ISupportFragment toFragment) {
        mSupportActivityDelegate.startWithPop(toFragment);
    }

    /**
     * It is recommended to use {@link SupportFragment#replaceFragment(ISupportFragment, boolean)}.
     */
    public void replaceFragment(ISupportFragment toFragment, boolean addToBackStack) {
        mSupportActivityDelegate.replaceFragment(toFragment, addToBackStack);
    }

    /**
     * Pop the fragment.
     */
    public void pop() {
        mSupportActivityDelegate.pop();
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     * <p>
     * 出栈到目标fragment
     *
     * @param targetFragmentClass   目标fragment
     * @param includeTargetFragment 是否包含该fragment
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mSupportActivityDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        mSupportActivityDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        mSupportActivityDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
    }

    /**
     * 当Fragment根布局 没有 设定background属性时,
     * Fragmentation默认使用Theme的android:windowbackground作为Fragment的背景,
     * 可以通过该方法改变其内所有Fragment的默认背景。
     */
    public void setDefaultFragmentBackground(@DrawableRes int backgroundRes) {
        mSupportActivityDelegate.setDefaultFragmentBackground(backgroundRes);
    }

    /**
     * 得到位于栈顶Fragment
     */
    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getSupportFragmentManager());
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mSupportActivityDelegate;
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return mSupportActivityDelegate.extraTransaction();
    }

    /**
     * 初始化 container
     */
    public void initContainer(){
        containerId = initContainerId();

        String fragment = getIntent().getStringExtra(CONSTANT.FRAGMENT);
        Bundle args = getIntent().getBundleExtra(CONSTANT.BUNDLE);

        if (fragment != null) {
            NxFragment target = (NxFragment) ARouter.getInstance()
                    .build(fragment)
                    .navigation();
            if (args != null) {
                target.setArguments(args);
            }
            loadRootFragment(target);
        }
    }

    /**
     * 初始化containerId
     *
     * @return
     */
    public int initContainerId() {
        if (containerId != INVALID.ID) {
            return containerId;
        }

        if (containerView != null && containerView.getId() == INVALID.ID) {
            containerView.setId(R.id.container); //设置 containerView的Id
        }
        return containerView.getId();
    }

    public int getContainerId() {
        if (containerId == INVALID.ID) {
            throw new RuntimeException("Please set containerId");
        }
        return containerId;
    }
}
