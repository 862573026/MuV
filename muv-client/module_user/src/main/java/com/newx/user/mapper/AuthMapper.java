package com.newx.user.mapper;


import com.newx.user.db.GreenDaoHelper;
import com.newx.user.entity.AuthInfo;

/**
 * Created by xuzhijian on 2018/5/11 0011.
 */

public class AuthMapper {


    public void deleteAll() {
        GreenDaoHelper.getSession().getAuthInfoDao().deleteAll();
    }

    public void insert(AuthInfo authInfo) {
        deleteAll();
        GreenDaoHelper.getSession().getAuthInfoDao().insert(authInfo);
    }

    public AuthInfo loadFirst() {
        return GreenDaoHelper.getSession().getAuthInfoDao()
                .queryBuilder()
                .unique();
    }
}
