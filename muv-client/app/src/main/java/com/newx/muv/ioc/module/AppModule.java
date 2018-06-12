package com.newx.muv.ioc.module;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.entity.gen.DaoMaster;
import com.newx.entity.gen.DaoSession;
import com.newx.muv.base.db.NSQLiteOpenHelper;
import com.newx.muv.ioc.scope.ApplicationScope;
import com.newx.muv.utils.rx.rxbus.RxBus;

import dagger.Module;
import dagger.Provides;


/**
 * Created by xuzhijian on 2018/4/26 0026.
 */
@Module
public class AppModule {

    Application mContext;

    public AppModule(Application mContext) {
        this.mContext = mContext;
    }

    @Provides
    @ApplicationScope
    Context provideContext() {
        return mContext;
    }


    @Provides
    @ApplicationScope
    RxBus provideRxBus() {
        return RxBus.getInstance();
    }

    @Provides
    @ApplicationScope
    ARouter provideARouter() {
        ARouter.openDebug();
        ARouter.init(mContext);
        return ARouter.getInstance();
    }

    @Provides
    @ApplicationScope
    DaoSession provideDaoSession() {
        //创建数据库 muv.db
        //MigrationHelper.DEBUG = true; //如果你想查看日志信息，请将DEBUG设置为true
        NSQLiteOpenHelper helper = new NSQLiteOpenHelper(mContext, "muv.db", null);
        //获取可写数据库
        SQLiteDatabase database = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        //获取Dao对象管理者
        return daoMaster.newSession();
    }

}
