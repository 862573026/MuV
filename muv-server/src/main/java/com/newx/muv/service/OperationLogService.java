package com.newx.muv.service;

import com.newx.muv.entity.bo.OperationLog;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 9:30 2018/4/22
 */
public interface OperationLogService {

    List<OperationLog> getOperationList();
}
