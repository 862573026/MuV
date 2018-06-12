package com.newx.muv.dao;

import com.newx.muv.entity.bo.UserRole;
import org.springframework.dao.DataAccessException;

public interface UserRoleMapper {

    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    int insert(UserRole record) throws DataAccessException;

    int insertSelective(UserRole record) throws DataAccessException;

    UserRole selectByPrimaryKey(Integer id) throws DataAccessException;

    int updateByPrimaryKeySelective(UserRole record) throws DataAccessException;

    int updateByPrimaryKey(UserRole record) throws DataAccessException;

    int deleteByUniqueKey(UserRole record) throws DataAccessException;
}