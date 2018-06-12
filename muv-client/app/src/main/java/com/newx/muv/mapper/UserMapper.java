package com.newx.muv.mapper;

import com.newx.entity.base.User;
import com.newx.entity.gen.UserDao;

/**
 * Created by newx on 18-5-12.
 * 用户信息的数据库操作
 */

public class UserMapper extends BaseMapper {


    public void deleteAll() {
        mDaoSession.getUserDao().deleteAll();
    }

    public void insert(User user) {
        deleteAll();
        mDaoSession.getUserDao().insert(user);
    }

    /**
     * 换成查询最近的
     *
     * @return
     */
    public User loadFirst() {
        return mDaoSession.getUserDao()
                .queryBuilder()
                .unique();
    }
}
