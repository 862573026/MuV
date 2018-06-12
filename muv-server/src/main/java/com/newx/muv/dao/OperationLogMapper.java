package com.newx.muv.dao;

import com.newx.muv.entity.bo.OperationLog;
import org.springframework.dao.DataAccessException;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 8:28 2018/4/22
 */
public interface OperationLogMapper {

    List<OperationLog> selectOperationLogList();

    int insertSelective(OperationLog operationLog) throws DataAccessException;
}
