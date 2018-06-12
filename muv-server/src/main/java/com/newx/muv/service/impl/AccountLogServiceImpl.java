package com.newx.muv.service.impl;

import com.newx.muv.dao.AccountLogMapper;
import com.newx.muv.entity.bo.AccountLog;
import com.newx.muv.service.AccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 9:32 2018/4/22
 */
@Service
public class AccountLogServiceImpl implements AccountLogService {

    @Autowired
    AccountLogMapper mAccountLogMapper;

    @Override
    public List<AccountLog> getAccountLogList() {
        return mAccountLogMapper.selectAccountLogList();
    }
}
