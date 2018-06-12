package com.newx.muv.shiro.provider.impl;


import com.newx.muv.entity.vo.Account;
import com.newx.muv.service.AccountService;
import com.newx.muv.shiro.provider.AccountProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/* *
 * @Author newx
 * @Description 
 * @Date 9:22 2018/2/13
 */
@Service("AccountProvider")
public class AccountProviderImpl implements AccountProvider {

      @Autowired
      @Qualifier("AccountService")
      private AccountService accountService;

    public Account loadAccount(String appId) {
        return accountService.loadAccount(appId);
    }
}
