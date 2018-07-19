package com.newx.muv.popular.vm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.os.Handler;

import com.newx.base.route.RP;
import com.newx.base.vm.NxBaseVM;
import com.newx.common.helper.JumpHelper;
import com.newx.common.proxy.ResourceProxy;
import com.newx.common.command.BindingCommand;
import com.newx.muv.popular.entity.PopularNewestEntity;
import com.newx.player.BR;
import com.newx.player.R;
import com.newx.player.def.PlayerConstant;
import com.newx.player.entity.VideoPreEntity;
import com.newx.utils.config.AssetsManager;
import com.newx.utils.number.NumberUtil;
import com.newx.utils.text.GsonUtil;

import me.tatarka.bindingcollectionadapter2.ItemBinding;


/**
 * Created by xuzhijian on 2018/4/17 0017.
 */

public class PopularVM extends NxBaseVM {

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
    public BindingCommand onRefreshCommand = new BindingCommand(() -> {
        //重新请求
        requestNewestPopular();
    });

    //上拉加载

    public BindingCommand onLoadMoreCommand = new BindingCommand(() -> requestMorePopular());


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
        String popularList = AssetsManager.getInstance().readFileFromAssets(ResourceProxy.getContext(), PlayerConstant.ASSET_TEST_POPULAR);
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
        JumpHelper.fragment(RP.SearchFragment)
                .navigation();
    }


}
