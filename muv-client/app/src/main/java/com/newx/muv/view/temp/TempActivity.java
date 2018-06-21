package com.newx.muv.view.temp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.databinding.library.baseAdapters.BR;
import com.mikepenz.materialdrawer.Drawer;
import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.widget.bar.ImmersionBar;
import com.newx.base.proxy.ResourceProxy;
import com.newx.base.ui.viewpager.indicator.IndicatorViewPager;
import com.newx.base.ui.viewpager.indicator.slidebar.ColorBar;
import com.newx.base.ui.viewpager.indicator.transition.OnTransitionTextListener;
import com.newx.base.utils.view.DisplayUtil;
import com.newx.muv.R;
import com.newx.muv.base.page.activity.NxInjectMvvMActivity;
import com.newx.muv.databinding.ActivityTempBinding;
import com.newx.muv.base.def.KEY;
import com.newx.muv.base.def.TAG;
import com.newx.muv.ioc.util.InjectConfig;
import com.newx.muv.proxy.MiddlewareProxy;
import com.newx.muv.view.route.ACTIVITY;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.ui.widget.combinetransfer.base.BindDefine;
import com.newx.muv.ui.widget.combinetransfer.observer.BindProperty;
import com.newx.muv.vm.TempVM;


/**
 * Created by xuzhijian on 2018/4/23 0023.
 */
@Route(path = ACTIVITY.TempActivity)
public class TempActivity extends NxInjectMvvMActivity<ActivityTempBinding, TempVM> {

    private IndicatorViewPager indicatorViewPager;

//    public ActivityTempBinding mViewBinding;

    private Drawer settingDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

//        initDrawer();
    }

    @Override
    public int initContainerId() {
        return R.id.layout_temp;
    }

    @Override
    public TempVM initVM() {
       return new TempVM();
    }

    private void initView() {
//        mViewBinding = mBinding;
        float barHeight = ResourceProxy.getDimension(R.dimen.px_5);

        int selectColor = ResourceProxy.getColor(R.color.DodgerBlue);
        int unSelectColor = ResourceProxy.getColor(R.color.Gray_AA);
        float unSelectSize = DisplayUtil.px2sp(ResourceProxy.getContext(),
                ResourceProxy.getDimension(R.dimen.font_smaller));
        float selectSize = unSelectSize * 1.2f;

        mBinding.tabTempIndicator.setScrollBar(new ColorBar(getApplicationContext(), selectColor, (int) barHeight));
        mBinding.tabTempIndicator.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(selectColor, unSelectColor)
                .setSize(selectSize, unSelectSize));


        indicatorViewPager = new IndicatorViewPager(mBinding.tabTempIndicator, mBinding.tabTempViewPager);

        TempAdapter adapter = new TempAdapter(getSupportFragmentManager());
        indicatorViewPager.setAdapter(adapter);

        mBinding.tabTempViewPager.setCurrentItem(1, false);

        mBinding.tabTempViewPager.setOffscreenPageLimit(3);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            setStatusPadding();
        }

        mBinding.tvToLogin.setProperty(BindProperty.newBuilder()
                .dimOut()
                .startOffset(0)
                .endOffset(BindDefine.DEFAULT_MAX_Y_OFFSET / 2)
                .build());

        mBinding.tvToLogin.setEventTag(TAG.EVENT_POPULAR_LIST_SCROLL);

//        mBinding.tabTempViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                switch (position) {
//                    case 1:
//                        mBinding.viewPadding.setVisibility(View.VISIBLE);
//                        break;
//                    default:
//                        mBinding.viewPadding.setVisibility(View.GONE);
//                        break;
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

//        mViewBinding.ivSearch.setProperty(BindProperty.newBuilder()
//                .scaleIn()
//                .startOffset(BindDefine.DEFAULT_MAX_Y_OFFSET / 2)
//                .endOffset(BindDefine.DEFAULT_MAX_Y_OFFSET)
//                .build());
//
//        mViewBinding.ivSearch.setVisibility(View.INVISIBLE);
//
//        mViewBinding.ivSearch.setEventTag(TAG.EVENT_POPULAR_LIST_SCROLL);
    }

    private void setStatusPadding() {
        mBinding.layoutTemp.setPadding(0, ImmersionBar.getStatusBarHeight(TempActivity.this), 0, 0);
    }


//    private void initDrawer() {
//        settingDrawer = new DrawerBuilder()
//                .withRootView(mViewBinding.layoutTemp)
//                .withActivity(this)
//                .withDrawerLayout(R.layout.drawer_setting) //看看官方demo，能不能在xml写布局
//                .addDrawerItems(new PrimaryDrawerItem().withName("登录"))
//                .withFullscreen(true)
//                .build();
//    }

    @Override
    public void initViewObservable() {
//        mViewModel.uc.settingClick.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
//            @Override
//            public void onPropertyChanged(Observable observable, int i) {
//                if (!settingDrawer.isDrawerOpen()) {
//                    settingDrawer.openDrawer();
//                }
//            }
//        });
    }

    @Override
    public int initContentView() {
        return R.layout.activity_temp;
    }

    @Override
    public int initVariableId() {
        return BR.tempVM;
    }

    private class TempAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        private LayoutInflater inflater;


        private String[] tabNames = {
                ResourceProxy.getString(R.string.live_text),
                ResourceProxy.getString(R.string.popular_text),
                ResourceProxy.getString(R.string.featured_text),};


        public TempAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            switch (position) {
                case 0:
                    return MiddlewareProxy.findFragment(FRAGMENT.LiveFragment);
                case 1:
                    Bundle bundle = new Bundle();
                    bundle.putFloat(KEY.KEY_BUNDLE_PADDING_TOP, ResourceProxy.getDimension(R.dimen.indicator_height));
                    Fragment popularFragment = MiddlewareProxy.findFragment(FRAGMENT.PopularFragment, bundle);
                    return popularFragment;
                case 2:
                    return MiddlewareProxy.findFragment(FRAGMENT.FeaturedFragment);
            }
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_UNCHANGED;
        }
    }


    public int getIndicatorHeight() {
        return mBinding.tabTempIndicator.getHeight();
    }


    @Override
    public InjectConfig injectConfig() {
        return InjectConfig.builder()
                .needClientInject(true)
                .build();
    }

}
