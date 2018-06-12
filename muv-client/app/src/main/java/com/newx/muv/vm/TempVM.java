package com.newx.muv.vm;

import com.newx.muv.base.page.InjectVM;
import com.newx.muv.helper.JumpHelper;
import com.newx.muv.ioc.util.InjectConfig;
import com.newx.muv.view.route.FRAGMENT;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by xuzhijian on 2018/4/17 0017.
 */

public class TempVM extends InjectVM {


    @Inject
    List<String> mList;

    @Inject
    public TempVM() {
    }


    //条目的点击事件
//    public BindingCommand settingClick = new BindingCommand(new BindingAction() {
//        @Override
//        public void call() {
//            uc.showSetting.set(!uc.showSetting.get());
//        }
//    });

    public void toLogin() {
//        uc.settingClick.set(!uc.settingClick.get());
//        ARouter.getInstance()
//                .build(ACTIVITY.LoginActivity)
//                .withString(FRAGMENT.KEY,FRAGMENT.LoginChooseFragment)
//                .navigation();
        JumpHelper.fragment(FRAGMENT.LoginChooseFragment)
                .navigation();
    }

    @Override
    public InjectConfig injectConfig() {
        return InjectConfig.builder()
                .needClientInject(true)
                .build();
    }

    //    public UIChangeObservable uc = new UIChangeObservable();
//
//    public class UIChangeObservable {
//        //是否显示Setting
//        public ObservableBoolean settingClick = new ObservableBoolean(false);
//
//    }


}
