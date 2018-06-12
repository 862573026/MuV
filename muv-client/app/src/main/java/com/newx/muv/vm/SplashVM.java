package com.newx.muv.vm;

import android.content.Context;
import android.databinding.ObservableInt;

import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.helper.LoginHelper;
import com.newx.muv.proxy.MiddlewareProxy;
import com.newx.muv.view.route.ACTIVITY;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by xuzhijian on 2018/4/18 0018.
 * 启动页 VM
 */

public class SplashVM extends NSBaseVM {

    private final int mCountDownTime = 2;

    private Context mContext;

    public ObservableInt countDown = new ObservableInt();

    private String mTargetActivity = ACTIVITY.TempActivity;

    private LoginHelper mLoginHelper;


    public SplashVM(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

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
        mLoginHelper = new LoginHelper();
        if (mLoginHelper.isLogin()) {
            MiddlewareProxy.getRuntimeDataManager().currentUser = mLoginHelper.currentUser();
            mTargetActivity = ACTIVITY.TabActivity;
        }


    }


    private void doSkip() {
        ARouter.getInstance()
                .build(mTargetActivity)
                .navigation();
        exit(mContext);
    }


}
