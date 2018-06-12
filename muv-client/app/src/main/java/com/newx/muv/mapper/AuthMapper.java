package com.newx.muv.mapper;

import com.newx.entity.base.AuthInfo;


/**
 * Created by xuzhijian on 2018/5/11 0011.
 */

public class AuthMapper extends BaseMapper {


    public void deleteAll() {
        mDaoSession.getAuthInfoDao().deleteAll();
    }

    public void insert(AuthInfo authInfo) {
        deleteAll();
        mDaoSession.getAuthInfoDao().insert(authInfo);
    }

    public AuthInfo loadFirst() {
        return mDaoSession.getAuthInfoDao()
                .queryBuilder()
                .unique();
    }
}
