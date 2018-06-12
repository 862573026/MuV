package com.newx.muv.service;

import com.newx.muv.entity.bo.User;
import com.newx.muv.entity.vo.Account;

/* *
 * @Author newx
 * @Description 
 * @Date 22:02 2018/3/7
 */
public interface AccountService {

    Account loadAccount(String appId);
    boolean isAccountExistByUid(String uid);
    boolean isAccountExistByUsername(String username);
    boolean registerAccount(User account);
    String loadAccountRole(String appId);
}
