package com.newx.muv.service.impl;

import com.newx.muv.dao.OperationLogMapper;
import com.newx.muv.entity.bo.OperationLog;
import com.newx.muv.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 9:34 2018/4/22
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    OperationLogMapper mOperationLogMapper;

    @Override
    public List<OperationLog> getOperationList() {
        return mOperationLogMapper.selectOperationLogList();
    }
}
