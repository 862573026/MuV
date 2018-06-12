package com.newx.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.newx.entity.gen.AuthInfoDao;
import com.newx.entity.gen.DaoMaster;
import com.newx.entity.gen.UserDao;
import com.newx.entity.gen.VideoInfoDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by newx on 18-5-12.
 * 数据库升级文件
 */

public class NxQLiteOpenHelper extends DaoMaster.OpenHelper {

    public NxQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

                    @Override
                    public void onCreateAllTables(Database db, boolean ifNotExists) {
                        DaoMaster.createAllTables(db, ifNotExists);
                    }

                    @Override
                    public void onDropAllTables(Database db, boolean ifExists) {
                        DaoMaster.dropAllTables(db, ifExists);
                    }
                }, AuthInfoDao.class, UserDao.class,
                VideoInfoDao.class);
    }
}
