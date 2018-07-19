package com.newx.user.provider.impl;

import android.content.Context;
import android.widget.Toast;

import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.user.entity.AuthInfo;
import com.newx.user.entity.User;
import com.newx.user.mapper.AuthMapper;
import com.newx.user.mapper.UserMapper;
import com.newx.user.provider.UserService;

/**
 * Created by xuzhijian on 2018/7/10 0010.
 */
@Route(path = "/service/user")
public class UserServiceImpl implements UserService {

    Context mContext;

    private AuthMapper mAuthMapper = new AuthMapper();
    private UserMapper mUserMapper = new UserMapper();

    /**
     * 登录状态
     * @return
     */
    @Override
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
    @Override
    public User currentUser() {
        return mUserMapper.loadFirst();
    }

    /**
     * 设置在线状态
     *
     * @param authInfo
     * @param loginUser
     */
    @Override
    public void setOnlineStatus(AuthInfo authInfo, User loginUser) {
        mAuthMapper.insert(authInfo);
        mUserMapper.insert(loginUser);
    }

    /**
     * 设置离线状态
     * 清除Auth就行，暂时不需要deleteByUid
     */
    @Override
    public void setOfflineStatus() {
        mAuthMapper.deleteAll();
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
