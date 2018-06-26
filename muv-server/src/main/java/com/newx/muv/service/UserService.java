package com.newx.muv.service;

import com.newx.muv.entity.bo.User;
import com.newx.muv.entity.bo.UserRoleInfo;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 21:14 2018/3/17
 */
public interface UserService {

    String loadAccountRole(String appId);

    List<User> getUserList();

    List<User> getUserListByRoleId(Integer roleId);

    boolean authorityUserRole(Long uid, int roleId);

    boolean deleteAuthorityUserRole(Long uid, int roleId);

    User getUserByAppId(String appId);

    List<User> getNotAuthorityUserListByRoleId(Integer roleId);

    List<UserRoleInfo> getUserRoleByUid(Integer uId);

}
