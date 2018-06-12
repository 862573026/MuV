package com.newx.muv.dao;

import com.newx.muv.entity.bo.AccountLog;
import org.springframework.dao.DataAccessException;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 8:27 2018/4/22
 */
public interface AccountLogMapper {

    List<AccountLog> selectAccountLogList();

    int insertSelective(AccountLog accountLog) throws DataAccessException;

}
