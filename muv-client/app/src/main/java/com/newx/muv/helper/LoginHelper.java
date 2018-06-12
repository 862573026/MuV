package com.newx.muv.helper;

import com.newx.entity.base.AuthInfo;
import com.newx.entity.base.User;
import com.newx.muv.mapper.AuthMapper;
import com.newx.muv.mapper.UserMapper;

/**
 * Created by newx on 18-5-20.
 * 登录帮助类
 */

public class LoginHelper {

    private AuthMapper mAuthMapper = new AuthMapper();
    private UserMapper mUserMapper = new UserMapper();

    /**
     * 登录状态
     * @return
     */
    public boolean isLogin() {
        String uidFromUser = mUserMapper.loadFirst() != null ? mUserMapper.loadFirst().getUid() : null;
        String uidFromAuth = mAuthMapper.loadFirst() != null ? mAuthMapper.loadFirst().getUid() : null;
        if (uidFromAuth != null
                && uidFromUser != null
                && uidFromUser.equals(uidFromAuth)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 当前登录用户
     * @return
     */
    public User currentUser() {
        return mUserMapper.loadFirst();
    }

    /**
     * 设置在线状态
     *
     * @param authInfo
     * @param loginUser
     */
    public void setOnlineStatus(AuthInfo authInfo, User loginUser) {
        mAuthMapper.insert(authInfo);
        mUserMapper.insert(loginUser);
    }

    /**
     * 设置离线状态
     * 清除Auth就行，暂时不需要deleteByUid
     */
    public void setOfflineStatus() {
        mAuthMapper.deleteAll();
    }
}
