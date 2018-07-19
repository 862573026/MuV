package com.newx.user.mapper;


import com.newx.user.db.GreenDaoHelper;
import com.newx.user.entity.User;

/**
 * Created by newx on 18-5-12.
 * 用户信息的数据库操作
 */

public class UserMapper {


    public void deleteAll() {
        GreenDaoHelper.getSession().getUserDao().deleteAll();
    }

    public void insert(User user) {
        deleteAll();
        GreenDaoHelper.getSession().getUserDao().insert(user);
    }

    /**
     * 换成查询最近的
     *
     * @return
     */
    public User loadFirst() {
        return GreenDaoHelper.getSession().getUserDao()
                .queryBuilder()
                .unique();
    }
}
