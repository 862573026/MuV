package com.newx.muv.service.impl;

import com.newx.muv.dao.RoleMapper;
import com.newx.muv.dao.RoleResourceMapper;
import com.newx.muv.entity.bo.Role;
import com.newx.muv.entity.bo.RoleResource;
import com.newx.muv.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 12:28 2018/3/26
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleResourceMapper mRoleResourceMapper;

    @Autowired
    private RoleMapper mRoleMapper;

    @Override
    public boolean authorityRoleResource(int roleId, int resourceId) throws DataAccessException {
        RoleResource roleResource = new RoleResource();
        roleResource.setRoleId(roleId);
        roleResource.setResourceId(resourceId);
        roleResource.setCreateTime(new Date());
        roleResource.setUpdateTime(new Date());
        return mRoleResourceMapper.insert(roleResource) == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean addRole(Role role) throws DataAccessException {
        int num = mRoleMapper.insertSelective(role);
        return num == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean updateRole(Role role) throws DataAccessException {
        int num = mRoleMapper.updateByPrimaryKeySelective(role);
        return num == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean deleteRoleByRoleId(Integer roleId) throws DataAccessException {
        int num = mRoleMapper.deleteByPrimaryKey(roleId);
        return num == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean deleteAuthorityRoleResource(Integer roleId, Integer resourceId) throws DataAccessException {
        int num = mRoleResourceMapper.deleteByUniqueKey(roleId, resourceId);
        return num == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<Role> getRoleList() throws DataAccessException {
        return mRoleMapper.selectRoles();
    }
}
