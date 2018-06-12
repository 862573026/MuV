package com.newx.muv.vm;

import android.databinding.Observable;
import android.databinding.ObservableField;

import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.helper.JumpHelper;
import com.newx.muv.view.route.ACTIVITY;
import com.newx.muv.view.route.FRAGMENT;

/**
 * Created by xuzhijian on 2018/5/10 0010.
 */

public class SearchVM extends NSBaseVM {

    public ObservableField<String> mSearchText = new ObservableField<>();


    @Override
    public void onCreate() {
        super.onCreate();
        mSearchText.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (mSearchText.get().equals("setting")
                        || mSearchText.get().equals("s")) { //调试用

                    JumpHelper.fragment(FRAGMENT.SettingFragment)
                            .navigation();
                }

                if(mSearchText.get().equals("t")){
                    ARouter.getInstance()
                            .build(ACTIVITY.NettyTestActivity)
                            .navigation();
                }
            }
        });
    }
}
