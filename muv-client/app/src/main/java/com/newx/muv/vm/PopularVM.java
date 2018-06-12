package com.newx.muv.vm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.os.Handler;

import com.newx.base.config.AssetsManager;
import com.newx.base.proxy.ResourceProxy;
import com.newx.base.utils.number.NumberUtil;
import com.newx.entity.PopularNewestEntity;
import com.newx.entity.base.VideoPreEntity;
import com.newx.muv.R;
import com.newx.muv.BR;
import com.newx.muv.base.databinding.command.BindingAction;
import com.newx.muv.base.databinding.command.BindingCommand;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.base.def.Constant;
import com.newx.muv.helper.JumpHelper;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.utils.text.GsonUtil;


import me.tatarka.bindingcollectionadapter2.ItemBinding;


/**
 * Created by xuzhijian on 2018/4/17 0017.
 */

public class PopularVM extends NSBaseVM {

    public ObservableList<String> mPopularSearch = new ObservableArrayList<>();

    public ItemBinding<ItemVideoPreVM> mItemBinding = ItemBinding.of(BR.itemVideoPre, R.layout.item_video_preview);
    public ObservableList<ItemVideoPreVM> mPopularList = new ObservableArrayList<>();

    public PopularVM() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //请求网络数据
        requestNewestPopular();
    }


    //下拉刷新
    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //重新请求
            requestNewestPopular();
        }
    });

    //上拉加载

    public BindingCommand onLoadMoreCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            requestMorePopular();
        }
    });


    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //下拉刷新完成的观察者
        public ObservableBoolean isFinishRefreshing = new ObservableBoolean(false);
        //上拉加载完成的观察者
        public ObservableBoolean isFinishLoadMore = new ObservableBoolean(false);
    }

    private void requestNewestPopular() {
        new Handler().postDelayed(() -> {
            uc.isFinishRefreshing.set(!uc.isFinishRefreshing.get());
            //模拟数据
            getNewestList();
        }, 100);
    }

    private void requestMorePopular() {
        new Handler().postDelayed(() -> {
            uc.isFinishLoadMore.set(!uc.isFinishLoadMore.get());
            //模拟数据
            getNewestList();

        }, 100);
    }

    private void getNewestList() {
        String popularList = AssetsManager.getInstance().readFileFromAssets(ResourceProxy.getContext(), Constant.ASSET_TEST_POPULAR);
        PopularNewestEntity entity = GsonUtil.toEntity(popularList, PopularNewestEntity.class);

        for (String search : entity.popularSearch) {
            mPopularSearch.add(search);
        }

        for (VideoPreEntity video : entity.popularList) {
            video.setLikeNum(NumberUtil.formatNum(video.getLikeNum(), false).toString());
            mPopularList.add(new ItemVideoPreVM(video));
        }
    }

    public void searchClick() {
        JumpHelper.fragment(FRAGMENT.SearchFragment)
                .navigation();
    }


}
