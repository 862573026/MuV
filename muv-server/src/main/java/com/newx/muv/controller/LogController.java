package com.newx.muv.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newx.muv.common.RespCode;
import com.newx.muv.common.RespKey;
import com.newx.muv.entity.bo.AccountLog;
import com.newx.muv.entity.bo.OperationLog;
import com.newx.muv.entity.vo.Message;
import com.newx.muv.service.AccountLogService;
import com.newx.muv.service.OperationLogService;
import com.newx.muv.util.RequestResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/account")
    public Message getAccountLogList(HttpServletRequest request) {
        RequestResponseUtil.pageHelper(request);
        List<AccountLog> accountLogs = accountLogService.getAccountLogList();
        PageInfo pageInfo = new PageInfo(accountLogs);
        return new Message().ok(RespCode.OK, "return accountLogs success").addData(RespKey.PAGE_INFO, pageInfo);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取用户操作api日志列表", httpMethod = "GET")
    @GetMapping("/operation")
    public Message getOperationLogList(HttpServletRequest request) {
        RequestResponseUtil.pageHelper(request);
        List<OperationLog> operationLogs = operationLogService.getOperationList();
        PageInfo pageInfo = new PageInfo(operationLogs);
        return new Message().ok(RespCode.OK, "return operationLogList success").addData(RespKey.PAGE_INFO, pageInfo);
    }
}
