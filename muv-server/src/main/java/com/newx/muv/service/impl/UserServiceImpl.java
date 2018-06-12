package com.newx.muv.service.impl;

import com.newx.muv.dao.UserMapper;
import com.newx.muv.dao.UserRoleMapper;
import com.newx.muv.entity.bo.User;
import com.newx.muv.entity.bo.UserRole;
import com.newx.muv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 21:15 2018/3/17
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper mUserRoleMapper;

    @Override
    public String loadAccountRole(String appId) throws DataAccessException {

        return userMapper.selectUserRoles(appId);
    }

    @Override
    public List<User> getUserList() throws DataAccessException {
        return userMapper.selectUserList();
    }

    @Override
    public List<User> getUserListByRoleId(Integer roleId) throws DataAccessException {
        return userMapper.selectUserListByRoleId(roleId);
    }

    @Override
    public boolean authorityUserRole(Long uid, int roleId) throws DataAccessException {
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(uid);
        userRole.setCreateTime(new Date());
        userRole.setUpdateTime(new Date());
        return mUserRoleMapper.insert(userRole) == 1? Boolean.TRUE :Boolean.FALSE;
    }

    @Override
    public boolean deleteAuthorityUserRole(Long uid, int roleId) throws DataAccessException {
        UserRole userRole = new UserRole();
        userRole.setUserId(uid);
        userRole.setRoleId(roleId);
        return mUserRoleMapper.deleteByUniqueKey(userRole) == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public User getUserByAppId(String appId) throws DataAccessException {

        return userMapper.selectByUniqueKey(appId);
    }

    @Override
    public List<User> getNotAuthorityUserListByRoleId(Integer roleId) throws DataAccessException {

        return userMapper.selectUserListExtendByRoleId(roleId);
    }
}
