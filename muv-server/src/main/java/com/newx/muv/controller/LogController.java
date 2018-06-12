package com.newx.muv.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newx.muv.common.RespCode;
import com.newx.muv.entity.bo.AccountLog;
import com.newx.muv.entity.bo.OperationLog;
import com.newx.muv.entity.vo.Message;
import com.newx.muv.service.AccountLogService;
import com.newx.muv.service.OperationLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 12:20 2018/4/22
 */
@RestController
@RequestMapping("/log")
public class LogController extends BasicAction {

    @Autowired
    AccountLogService accountLogService;

    @Autowired
    OperationLogService operationLogService;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取日志记录", httpMethod = "GET")
    @RequestMapping("/accountLog/{currentPage}/{pageSize}")
    public Message getAccountLogList(@PathVariable Integer currentPage, @PathVariable Integer pageSize ) {
        PageHelper.startPage(currentPage, pageSize);
        List<AccountLog> accountLogs = accountLogService.getAccountLogList();
        PageInfo pageInfo = new PageInfo(accountLogs);
        return new Message().ok(RespCode.OK, "return accountLogs success").addData("data",pageInfo);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取用户操作api日志列表", httpMethod = "GET")
    @RequestMapping("/operationLog/{currentPage}/{pageSize}")
    public Message getOperationLogList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<OperationLog> operationLogs = operationLogService.getOperationList();
        PageInfo pageInfo = new PageInfo(operationLogs);
        return new Message().ok(RespCode.OK, "return operationLogList success").addData("data", pageInfo);
    }
}
