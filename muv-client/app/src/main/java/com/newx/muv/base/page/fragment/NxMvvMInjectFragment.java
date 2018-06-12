package com.newx.muv.base.page.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newx.base.frameworks.support.mvvm.BaseViewModel;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.ioc.util.InjectConfig;
import com.newx.muv.ioc.util.InjectProxy;

/**
 * Created by xuzhijian on 2018/6/11 0011.
 */

public abstract class NxMvvMInjectFragment<V extends ViewDataBinding, VM extends BaseViewModel>
        extends NxMvvMFragment<V,VM> {

    public InjectProxy mInjectProxy;
    public InjectConfig mInjectConfig;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectOnCreate();
    }

    //Dagger注入功能
    private void injectOnCreate(){
        mInjectConfig = injectConfig();
        if (mInjectConfig != null) {
            mInjectProxy = new InjectProxy(mInjectConfig);
            mInjectProxy.onCreate();
        }
    }

    public InjectConfig injectConfig(){
        return null;
    }
}
