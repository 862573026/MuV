package com.newx.muv.view.tab.muv.popular;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.util.log.NXLog;
import com.newx.base.ui.refreshlayout.api.RefreshFooter;
import com.newx.base.ui.refreshlayout.api.RefreshHeader;
import com.newx.base.ui.refreshlayout.api.RefreshLayout;
import com.newx.base.ui.refreshlayout.constant.RefreshState;
import com.newx.base.ui.refreshlayout.listener.OnMultiPurposeListener;
import com.newx.muv.R;
import com.newx.muv.BR;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentPopularBinding;
import com.newx.muv.base.def.KEY;
import com.newx.muv.base.def.TAG;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.ui.widget.combinetransfer.base.BindDefine;
import com.newx.muv.ui.widget.combinetransfer.observer.BindProperty;
import com.newx.muv.vm.PopularVM;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


/**
 * Created by newx on 18-4-8.
 * 热门页面
 */
@Route(path = FRAGMENT.PopularFragment)
public class PopularFragment extends NxMvvMFragment<FragmentPopularBinding, PopularVM> {

    private Disposable taskSearchScroll;
    private int mRecyclerViewMaxOffset = BindDefine.DEFAULT_MAX_Y_OFFSET;

    private float paddingTop = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paddingTop = getArguments().getFloat(KEY.KEY_BUNDLE_PADDING_TOP);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        bindTransfer();

        contentView.setPadding(0, (int) paddingTop, 0, 0);

        taskSearchScroll = Flowable.interval(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(count -> {
                    if (mViewModel != null && mViewModel.mPopularSearch.size() > 0 && getUserVisibleHint()) {
                        mBinding.tvPopularSearch.setText(mViewModel.mPopularSearch.get(count.intValue() % mViewModel.mPopularSearch.size()));
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

        mBinding.listPopular.setEventTag(TAG.EVENT_POPULAR_LIST_SCROLL);

        mBinding.layoutSearch.setProperty(BindProperty.newBuilder()
                .startOffset(0)
                .endOffset(mBinding.listPopular.getMaxYOffset() / 2)
                .dimOut()
                .build());

        mBinding.layoutSearch.setEventTag(TAG.EVENT_POPULAR_LIST_SCROLL);

    }

}
