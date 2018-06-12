package com.newx.muv.view.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.support.mvvm.NxBindingActivity;
import com.newx.base.proxy.ResourceProxy;
import com.newx.base.ui.viewpager.indicator.IndicatorViewPager;
import com.newx.base.ui.viewpager.indicator.transition.OnTransitionTextListener;
import com.newx.muv.R;
import com.newx.muv.databinding.ActivityTabBinding;
import com.newx.muv.helper.JumpHelper;
import com.newx.muv.view.route.ACTIVITY;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.proxy.MiddlewareProxy;

/**
 * Created by xuzhijian on 2018/5/19 0019.
 * Tab 主页面
 */
@Route(path = ACTIVITY.TabActivity)
public class TabActivity extends NxBindingActivity<ActivityTabBinding> {

    private IndicatorViewPager indicatorViewPager;
    private View centerView;


    @Override
    public int initContentView() {
        return R.layout.activity_tab;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        mBinding.tabMainIndicator.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.RED, Color.GRAY));

        //这里可以添加一个view，作为centerView，会位于Indicator的tab的中间
        centerView = getLayoutInflater().inflate(R.layout.tab_main_center, mBinding.tabMainIndicator, false);
        mBinding.tabMainIndicator.setCenterView(centerView);
        centerView.setOnClickListener(v -> JumpHelper.fragment(FRAGMENT.UploadFragment).navigation());

        indicatorViewPager = new IndicatorViewPager(mBinding.tabMainIndicator, mBinding.tabMainViewPager);
        indicatorViewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        // 禁止viewpager的滑动事件
        mBinding.tabMainViewPager.setCanScroll(false);
        // 设置viewpager保留界面不重新加载的页面数量
        mBinding.tabMainViewPager.setOffscreenPageLimit(4);


//        indicatorViewPager.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                switch (position) {
//                    case 0:
//                        DisplayUtil.setFullScreen(TabActivity.this);
//                        break;
//                    default:
//                        DisplayUtil.exitFullScreen(TabActivity.this);
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
    }

    private class TabAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        private String[] tabNames = {
                ResourceProxy.getString(R.string.muv_text),
                ResourceProxy.getString(R.string.my_focus_text),
                ResourceProxy.getString(R.string.classify_text),
                ResourceProxy.getString(R.string.me_text),};
        private int[] tabIcons = {R.drawable.maintab_1_selector, R.drawable.maintab_2_selector, R.drawable.maintab_3_selector,
                R.drawable.maintab_4_selector};
        private LayoutInflater inflater;

        public TabAdapter(FragmentManager fragmentManager) {
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
                convertView = inflater.inflate(R.layout.tab_main, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabNames[position]);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
            return textView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            switch (position) {
                case 0:
                    return MiddlewareProxy.findFragment(FRAGMENT.MuVRootFragment);
                case 1:
                    return MiddlewareProxy.findFragment(FRAGMENT.FocusFragment);
                case 2:
                    return MiddlewareProxy.findFragment(FRAGMENT.ClassifyFragment);
                case 3:
                    return MiddlewareProxy.findFragment(FRAGMENT.MeRootFragment);
            }
            return null;
        }
    }


}
