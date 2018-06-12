package com.newx.muv.service;

import com.newx.muv.entity.bo.Resource;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 13:39 2018/3/18
 */
public interface ResourceService {

    List<Resource> getAuthorityMenusByUid(String appId);

    List<Resource> getMenus();

    Boolean addMenu(Resource menu);

    Boolean modifyMenu(Resource menu);

    Boolean deleteMenuByMenuId(Integer menuId);


    List<Resource> getApiTeamList();

    List<Resource> getApiList();

    List<Resource> getApiListByTeamId(Integer teamId);

    List<Resource> getAuthorityApisByRoleId(Integer roleId);

    List<Resource> getAuthorityMenusByRoleId(Integer roleId);

    List<Resource> getNotAuthorityApisByRoleId(Integer roleId);

    List<Resource> getNotAuthorityMenusByRoleId(Integer roleId);
}
