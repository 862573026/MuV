package com.newx.user.db;

import android.database.sqlite.SQLiteDatabase;

import com.newx.common.proxy.ResourceProxy;
import com.newx.user.entity.gen.DaoMaster;
import com.newx.user.entity.gen.DaoSession;

/**
 * Created by xuzhijian on 2018/7/7 0007.
 */

public class GreenDaoHelper {

    //单例
    private volatile static GreenDaoHelper mInstance = null;
    private DaoSession mSession;

    private GreenDaoHelper(){
        //创建数据库 muv.db
        //MigrationHelper.DEBUG = true; //如果你想查看日志信息，请将DEBUG设置为true
        NSQLiteOpenHelper helper = new NSQLiteOpenHelper(ResourceProxy.getContext(), "muv_user.db", null);
        //获取可写数据库
        SQLiteDatabase database = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        mSession = daoMaster.newSession();
    }

    private static GreenDaoHelper getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoHelper.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoHelper();
                }
            }
        }
        return mInstance;
    }

    public static DaoSession getSession() {
        return getInstance().mSession;
    }
}
