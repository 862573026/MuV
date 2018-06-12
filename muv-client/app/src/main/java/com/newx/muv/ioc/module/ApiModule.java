package com.newx.muv.ioc.module;

import android.app.Application;

import dagger.Module;

/**
 * Created by newx on 18-4-26.
 */
@Module
public class ApiModule {

    Application mContext;

    public ApiModule(Application mContext) {
        this.mContext = mContext;
    }


}
