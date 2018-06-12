package com.newx.muv.base.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.newx.muv.ioc.util.InjectConfig;
import com.newx.muv.ioc.util.InjectProxy;
import com.newx.muv.ioc.ComponentHelper;
import com.newx.muv.ioc.component.AppComponent;
import com.newx.muv.ioc.component.DaggerAppComponent;
import com.newx.muv.ioc.module.ApiModule;
import com.newx.muv.ioc.module.AppModule;


/**
 * Created by xuzhijian on 2018/5/2 0002.
 * App 委托
 */

public class AppDelegate implements AppLifecycles {

    public InjectProxy mInjectProxy;
    public InjectConfig mInjectConfig;

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {

        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .apiModule(new ApiModule(application))
                .build();

        ComponentHelper.setComponent(appComponent);

        mInjectConfig = InjectConfig
                .builder()
                .needClientInject(true)
                .build();

        if (mInjectConfig != null) {
            mInjectProxy = new InjectProxy(mInjectConfig);
            mInjectProxy.onCreate();
        }

    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
