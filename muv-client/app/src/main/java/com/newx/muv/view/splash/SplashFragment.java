package com.newx.muv.view.splash;

import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.os.Bundle;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.utils.utilcode.util.BarUtils;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentFlashBinding;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.SplashVM;

/**
 * Created by xuzhijian on 2018/4/18 0018.
 */
@Route(path = FRAGMENT.SplashFragment)
public class SplashFragment extends NxMvvMFragment<FragmentFlashBinding, SplashVM> {

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_flash;
    }

    @Override
    public void initViewObservable() {
        mViewModel.countDown.addOnPropertyChangedCallback(new ObservableInt.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                ObservableInt time = (ObservableInt) observable;
                mBinding.tvFlashCountDown.setText("跳过" + time.get() + "秒");
            }
        });
    }

    @Override
    public SplashVM initVM() {
        return new SplashVM(getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setStatusBarVisibility(getActivity(),false);
    }
}

