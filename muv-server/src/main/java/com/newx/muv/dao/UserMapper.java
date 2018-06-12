package com.newx.muv.dao;

import com.newx.muv.entity.bo.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(String uid) throws DataAccessException;

    int insert(User record) throws DataAccessException;

    int insertSelective(User record) throws DataAccessException;

    User selectByPrimaryKey(String uid) throws DataAccessException;

    int updateByPrimaryKeySelective(User record) throws DataAccessException;

    int updateByPrimaryKey(User record) throws DataAccessException;

    String selectUserRoles(String appId) throws DataAccessException;

    User selectByUniqueKey(String appId) throws DataAccessException;

    User selectByUsername(String username) throws DataAccessException;

    Long selectUidByUsername(String username) throws DataAccessException;

    List<User> selectUserList() throws DataAccessException;

    List<User> selectUserListByRoleId(Integer roleId) throws DataAccessException;

    List<User> selectUserListExtendByRoleId(Integer roleId) throws DataAccessException;
}