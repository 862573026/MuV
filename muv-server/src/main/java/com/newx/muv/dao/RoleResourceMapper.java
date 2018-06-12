package com.newx.muv.dao;

import com.newx.muv.entity.bo.RoleResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface RoleResourceMapper {

    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    int insert(RoleResource record) throws DataAccessException;

    int insertSelective(RoleResource record) throws DataAccessException;

    RoleResource selectByPrimaryKey(Integer id) throws DataAccessException;

    int updateByPrimaryKeySelective(RoleResource record) throws DataAccessException;

    int updateByPrimaryKey(RoleResource record) throws DataAccessException;

    int deleteByUniqueKey(@Param("roleId") Integer roleId, @Param("resourceId") Integer resourceId) throws DataAccessException;
}