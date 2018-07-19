package com.newx.muv.popular.view;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.common.proxy.ResourceProxy;
import com.newx.muv.popular.vm.PopularVM;
import com.newx.player.BR;
import com.newx.player.R;
import com.newx.player.databinding.FragmentPopularBinding;
import com.newx.player.def.PlayerKey;
import com.newx.ui.combinetransfer.base.BindDefine;
import com.newx.ui.combinetransfer.observer.BindProperty;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


/**
 * Created by newx on 18-4-8.
 * 热门页面
 */
@Route(path = RP.PopularFragment)
public class PopularFragment extends NxMvvMFragment<FragmentPopularBinding, PopularVM> {

    private Disposable taskSearchScroll;
    private int mRecyclerViewMaxOffset = BindDefine.DEFAULT_MAX_Y_OFFSET;
    private int mPaddingBottom;

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        mPaddingBottom = (int) getArguments().getFloat(PlayerKey.KEY_BUNDLE_PADDING_BOTTOM);
        contentView.setPadding(0, (int) ResourceProxy.getDimension(R.dimen.default_bar_height), 0, mPaddingBottom);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        bindTransfer();
        taskSearchScroll = Flowable.interval(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(count -> {
                    if (mViewModel != null && mViewModel.mPopularSearch.size() > 0 && getUserVisibleHint()) {
                        mBinding.tvPopularSearch.setText(
                                mViewModel.mPopularSearch.get(count.intValue() % mViewModel.mPopularSearch.size()));
                    }
                })
                .subscribe();
    }

    @Override
    public PopularVM initVM() {
        return new PopularVM();
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_popular;
    }

    @Override
    public int initVariableId() {
        return BR.popularVM;
    }

    @Override
    public void initViewObservable() {
        //监听下拉刷新
        mViewModel.uc.isFinishRefreshing.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                //结束刷新
                mBinding.refreshLayout.finishRefresh();
            }
        });

        //监听上拉加载
        mViewModel.uc.isFinishLoadMore.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                //结束加载
                mBinding.refreshLayout.finishLoadMore();
            }
        });
    }

    /**
     * 绑定联动
     */
    private void bindTransfer() {

        mBinding.listPopular.setEventTag(PlayerKey.EVENT_POPULAR_LIST_SCROLL);

        mBinding.layoutSearch.setProperty(BindProperty.newBuilder()
                .startOffset(0)
                .endOffset(mBinding.listPopular.getMaxYOffset() / 2)
                .dimOut()
                .build());

        mBinding.layoutSearch.setEventTag(PlayerKey.EVENT_POPULAR_LIST_SCROLL);

    }

}
