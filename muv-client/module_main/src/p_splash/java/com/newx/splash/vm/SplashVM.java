package com.newx.splash.vm;

import android.content.Context;
import android.databinding.ObservableInt;


import com.newx.base.route.RP;
import com.newx.common.frameworks.route.launcher.ARouter;
import com.newx.muv.main.provider.UserHelper;
import com.newx.base.vm.NxBaseVM;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by xuzhijian on 2018/4/18 0018.
 * 启动页 VM
 */

public class SplashVM extends NxBaseVM {

    private final int mCountDownTime = 2;

    private Context mContext;

    public ObservableInt countDown = new ObservableInt();

    private String mTargetActivity = RP.TempActivity;

    public SplashVM(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.getInstance().inject(this);

        countDown();
        authCheck();
    }

    /**
     * 倒计时任务
     */
    public void countDown() {
        Flowable.intervalRange(0, mCountDownTime, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(time -> countDown.set(mCountDownTime - time.intValue()))
                .doOnComplete(() -> doSkip())
                .subscribe();
    }

    /**
     * 身份验证任务
     */
    public void authCheck() {
//        mLoginHelper = new LoginHelper();
        if (UserHelper.isLogin()) {
//            MiddlewareProxy.getRuntimeDataManager().currentUser = UserHelper.currentUser();
            mTargetActivity = RP.TabActivity;
        }
    }


    private void doSkip() {
        ARouter.getInstance()
                .build(mTargetActivity)
                .navigation();
        exit(mContext);
    }


}
