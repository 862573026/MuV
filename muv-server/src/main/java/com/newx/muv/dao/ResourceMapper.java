package com.newx.muv.dao;

import com.newx.muv.entity.bo.Resource;
import com.newx.muv.shiro.rule.RolePermRule;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    int insert(Resource record) throws DataAccessException;

    int insertSelective(Resource record) throws DataAccessException;

    Resource selectByPrimaryKey(Integer id) throws DataAccessException;

    int updateByPrimaryKeySelective(Resource record) throws DataAccessException;

    int updateByPrimaryKey(Resource record) throws DataAccessException;

    List<RolePermRule> selectRoleRules()  throws DataAccessException;

    List<Resource> selectAuthorityMenusByUid(String appId) throws DataAccessException;

    List<Resource> selectMenus() throws DataAccessException;

    List<Resource> selectApiTeamList() throws DataAccessException;

    List<Resource> selectApiList() throws DataAccessException;

    List<Resource> selectApiListByTeamId(Integer teamId) throws DataAccessException;

    List<Resource> selectApisByRoleId(Integer roleId) throws DataAccessException;

    List<Resource> selectMenusByRoleId(Integer roleId) throws DataAccessException;

    List<Resource> selectNotAuthorityApisByRoleId(Integer roleId) throws DataAccessException;

    List<Resource> selectNotAuthorityMenusByRoleId(Integer roleId) throws DataAccessException;
}