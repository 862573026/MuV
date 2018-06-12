package com.newx.muv.base.page;

import com.newx.base.frameworks.support.mvvm.BaseViewModel;
import com.newx.muv.ioc.util.InjectConfig;
import com.newx.muv.ioc.util.InjectProxy;

/**
 * Created by xuzhijian on 2018/5/7 0007.
 */

public abstract class InjectVM extends BaseViewModel {

    public InjectProxy mInjectProxy;
    public InjectConfig mInjectConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        mInjectConfig = injectConfig();
        if (mInjectConfig != null) {
            mInjectProxy = new InjectProxy(mInjectConfig);
            mInjectProxy.onCreate();
        }
    }

    @Override
    public void lazyLoad() {

    }

    public abstract InjectConfig injectConfig();

}
