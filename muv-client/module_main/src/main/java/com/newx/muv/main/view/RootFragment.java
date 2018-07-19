package com.newx.muv.main.view;

import com.newx.common.frameworks.support.fragment.NxFragment;
import com.newx.common.frameworks.support.mvvm.NxBindingFragment;
import com.newx.muv.main.R;
import com.newx.muv.main.databinding.FragmentRootBinding;
import com.newx.ui.fragmentation.ISupportFragment;
/**
 * Created by xuzhijian on 2018/5/19 0019.
 */

public class RootFragment extends NxBindingFragment<FragmentRootBinding> {

    public NxFragment mRootFragment;

    @Override
    public int initContentView() {
        return R.layout.fragment_root;
    }

    @Override
    public int initContainerId() {
        return R.id.layout_root;
    }

    @Override
    public void loadRootFragment(ISupportFragment toFragment) {
        super.loadRootFragment(toFragment);
        mRootFragment = (NxFragment) toFragment;
    }

    @Override
    public void loadRootFragment(ISupportFragment toFragment, boolean addToBackStack, boolean allowAnim) {
        super.loadRootFragment(toFragment, addToBackStack, allowAnim);
        mRootFragment = (NxFragment) toFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // 相当于onResume()方法
            if (mRootFragment != null) {
                mRootFragment.setToolbarVisible(true);
            }
        } else {
            // 相当于onPause()方法
            if (mRootFragment != null) {
                mRootFragment.setToolbarVisible(false);
            }
        }
    }

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
//            DialogBuilderHelper.baseTipChooseDialog(getContext())
//                    .content("确认退出")
//                    .onPositive((dialog, which) -> _mActivity.finish())
//                    .onAny((dialog, which) -> dialog.dismiss())
//                    .build().show();
//            _mActivity.finish();
            return false;
        }
        return true;
    }


}
