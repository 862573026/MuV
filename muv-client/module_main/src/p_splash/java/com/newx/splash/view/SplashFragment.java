package com.newx.splash.view;

import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.os.Bundle;

import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.main.R;
import com.newx.muv.main.databinding.FragmentFlashBinding;
import com.newx.splash.vm.SplashVM;
import com.newx.utils.utilcode.util.BarUtils;


/**
 * Created by xuzhijian on 2018/4/18 0018.
 */
@Route(path = RP.SplashFragment)
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

