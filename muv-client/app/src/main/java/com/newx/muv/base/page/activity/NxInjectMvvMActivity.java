package com.newx.muv.base.page.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.newx.base.frameworks.support.mvvm.BaseViewModel;
import com.newx.base.frameworks.support.mvvm.NxMvvMActivity;
import com.newx.muv.ioc.util.InjectConfig;
import com.newx.muv.ioc.util.InjectProxy;

/**
 * Created by xuzhijian on 2018/5/7 0007.
 * 实现Dagger注入
 */
public abstract class NxInjectMvvMActivity<V extends ViewDataBinding, VM extends BaseViewModel>
        extends NxMvvMActivity<V,VM> {
    //==============================[ 参数部分 ]==============================
    //Dagger注入
    public InjectProxy mInjectProxy;
    public InjectConfig mInjectConfig;

    //
    //========================================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInjectConfig = injectConfig();
        if (mInjectConfig != null) {
            mInjectProxy = new InjectProxy(mInjectConfig);
            mInjectProxy.onCreate();
        }
    }


    //==============================[ 拓展方法 ]==============================
    //Dagger注入
    public InjectConfig injectConfig() {
        return null;
    }
    //========================================================================

}
