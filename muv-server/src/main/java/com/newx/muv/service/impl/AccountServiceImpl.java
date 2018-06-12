package com.newx.muv.service.impl;

import com.newx.muv.dao.UserMapper;
import com.newx.muv.entity.bo.User;
import com.newx.muv.entity.vo.Account;
import com.newx.muv.service.AccountService;
import com.newx.muv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/* *
 * @Author newx
 * @Description 
 * @Date 22:04 2018/3/7
 */
@Service("AccountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public Account loadAccount(String appId) throws DataAccessException {
        User user = userMapper.selectByUniqueKey(appId);
        return user != null ? new Account(user.getUsername(), user.getPassword(), user.getSalt()) : null;
    }

    @Override
    public boolean isAccountExistByUid(String uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean isAccountExistByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean registerAccount(User account) throws DataAccessException {

        // 给新用户授权访客角色
        if (userMapper.insertSelective(account) == 1) {
            Long uid = userMapper.selectUidByUsername(account.getUsername());
            userService.authorityUserRole(uid, 103);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public String loadAccountRole(String appId) throws DataAccessException {

        return userMapper.selectUserRoles(appId);
    }


}
