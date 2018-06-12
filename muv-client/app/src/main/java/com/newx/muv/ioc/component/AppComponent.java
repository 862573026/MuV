package com.newx.muv.ioc.component;


import android.content.Context;


import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.entity.gen.DaoSession;
import com.newx.muv.ioc.util.ClientInject;
import com.newx.muv.mapper.BaseMapper;
import com.newx.muv.utils.rx.rxbus.RxBus;
import com.newx.muv.base.delegate.AppDelegate;
import com.newx.muv.ioc.module.ApiModule;
import com.newx.muv.ioc.module.AppModule;
import com.newx.muv.ioc.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xuzhijian on 2018/4/26 0026.
 */
@ApplicationScope
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    void inject(AppDelegate app);

    void injectClient(ClientInject clientInject);

    void injectMapper(BaseMapper mapper);

    Context context();

    ARouter arouter();

    RxBus rxBus();

    DaoSession daoSession();

}
