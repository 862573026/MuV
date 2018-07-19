package com.newx.muv.main.view.temp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newx.base.proxy.MiddlewareProxy;
import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.NxMvvMActivity;
import com.newx.common.proxy.ResourceProxy;
import com.newx.muv.main.BR;
import com.newx.muv.main.R;
import com.newx.muv.main.databinding.ActivityTempBinding;
import com.newx.ui.combinetransfer.base.BindDefine;
import com.newx.ui.combinetransfer.observer.BindProperty;
import com.newx.muv.popular.vm.TempVM;
import com.newx.ui.viewpager.indicator.IndicatorViewPager;
import com.newx.ui.viewpager.indicator.slidebar.ColorBar;
import com.newx.ui.viewpager.indicator.transition.OnTransitionTextListener;
import com.newx.utils.def.TAG;
import com.newx.utils.utilcode.util.ConvertUtils;


/**
 * Created by xuzhijian on 2018/4/23 0023.
 */
@Route(path = RP.TempActivity)
public class TempActivity extends NxMvvMActivity<ActivityTempBinding, TempVM> {

    private IndicatorViewPager indicatorViewPager;


    //    private Drawer settingDrawer;

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
       return new TempVM(this);
    }


    @Override
    public int initContentView() {
        return R.layout.activity_temp;
    }

    @Override
    public int initVariableId() {
        return BR.tempVM;
    }

    private void initView() {
        float barHeight = ResourceProxy.getDimension(R.dimen.px_5);

        int selectColor = ResourceProxy.getColor(R.color.blue_dodger);
        int unSelectColor = ResourceProxy.getColor(R.color.gray_aa);
        float unSelectSize = ConvertUtils.px2sp(ResourceProxy.getDimension(R.dimen.font_smaller));
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


////    private void initDrawer() {
////        settingDrawer = new DrawerBuilder()
////                .withRootView(mViewBinding.layoutTemp)
////                .withActivity(this)
////                .withDrawerLayout(R.layout.drawer_setting) //看看官方demo，能不能在xml写布局
////                .addDrawerItems(new PrimaryDrawerItem().withName("登录"))
////                .withFullscreen(true)
////                .build();
////    }
//
//    @Override
//    public void initViewObservable() {
////        mViewModel.uc.settingClick.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
////            @Override
////            public void onPropertyChanged(Observable observable, int i) {
////                if (!settingDrawer.isDrawerOpen()) {
////                    settingDrawer.openDrawer();
////                }
////            }
////        });
//    }


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
                    return MiddlewareProxy.findFragment(RP.LiveFragment);
                case 1:
                    Fragment popularFragment = MiddlewareProxy.findFragment(RP.PopularFragment);
                    return popularFragment;
                case 2:
                    return MiddlewareProxy.findFragment(RP.LiveFragment);
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


//    @Override
//    public InjectConfig injectConfig() {
//        return InjectConfig.builder()
//                .needClientInject(true)
//                .build();
//    }

}
