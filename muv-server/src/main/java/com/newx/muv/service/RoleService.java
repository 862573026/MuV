package com.newx.muv.service;

import com.newx.muv.entity.bo.Role;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 9:10 2018/3/20
 */
public interface RoleService {


    boolean authorityRoleResource(int roleId, int resourceId);

    boolean addRole(Role role);

    boolean updateRole(Role role);

    boolean deleteRoleByRoleId(Integer roleId);

    boolean deleteAuthorityRoleResource(Integer roleId, Integer resourceId);

    List<Role> getRoleList();
}
