package com.newx.muv.dao;

import com.newx.muv.entity.bo.UserRole;
import com.newx.muv.entity.bo.UserRoleInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserRoleMapper {

    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    int insert(UserRole record) throws DataAccessException;

    int insertSelective(UserRole record) throws DataAccessException;

    UserRole selectByPrimaryKey(Integer id) throws DataAccessException;

    int updateByPrimaryKeySelective(UserRole record) throws DataAccessException;

    int updateByPrimaryKey(UserRole record) throws DataAccessException;

    int deleteByUniqueKey(UserRole record) throws DataAccessException;

    List<UserRoleInfo> getUserRoleByUid(Integer uid) throws DataAccessException;

    List<String> getUserRoleCodeByUid(Integer uid) throws DataAccessException;

}