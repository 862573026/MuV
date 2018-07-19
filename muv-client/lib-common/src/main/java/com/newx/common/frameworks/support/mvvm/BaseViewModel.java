package com.newx.common.frameworks.support.mvvm;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;


/**
 * Created by xuzhijian on 2018/4/17 0017.
 */

public abstract class BaseViewModel extends ViewModel implements IBaseViewModel {

    public BaseViewModel() {
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

//    /**
//     * 注入参数
//     * @return
//     */
//    public abstract InjectConfig injectConfig();

    public void exit(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.finish();
        }
    }


}
