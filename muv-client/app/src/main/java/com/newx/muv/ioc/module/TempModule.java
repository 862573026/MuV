package com.newx.muv.ioc.module;

import com.newx.muv.ioc.scope.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xuzhijian on 2018/5/7 0007.
 */

@Module
public class TempModule {

    public TempModule() {

    }

    @ActivityScope
    @Provides
    List<String> provideList() {
        return new ArrayList<>();
    }

}