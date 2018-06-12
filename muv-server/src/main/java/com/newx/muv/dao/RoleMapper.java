package com.newx.muv.dao;

import com.newx.muv.entity.bo.Role;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    int insert(Role record) throws DataAccessException;

    int insertSelective(Role record) throws DataAccessException;

    Role selectByPrimaryKey(Integer id) throws DataAccessException;

    int updateByPrimaryKeySelective(Role record) throws DataAccessException;

    int updateByPrimaryKey(Role record) throws DataAccessException;

    List<Role> selectRoles() throws DataAccessException;
}