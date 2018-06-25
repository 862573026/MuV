package com.newx.muv.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newx.muv.common.RespCode;
import com.newx.muv.common.RespKey;
import com.newx.muv.entity.bo.User;
import com.newx.muv.entity.vo.Message;
import com.newx.muv.service.UserService;
import com.newx.muv.util.JsonWebTokenUtil;
import com.newx.muv.util.RequestResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* *
 * @Author newx
 * @Description 用户相关操作
 * @Date 21:05 2018/3/17
 */
@RestController
@RequestMapping("/user")
public class UserController extends BasicAction{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @ApiOperation(value = "获取对应用户角色",notes = "GET根据用户的appId获取对应用户的角色")
    @GetMapping("/role/{appId}")
    public Message getUserRoleList(@PathVariable String appId) {

        String roles = userService.loadAccountRole(appId);
        Set<String> roleSet = JsonWebTokenUtil.split(roles);
        LOGGER.info(roleSet.toString());
        return new Message().ok(RespCode.OK,"return roles success").addData("roles",roleSet);
    }


    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取用户列表",notes = "GET获取所有注册用户的信息列表")
    @GetMapping("/list")
    public Message getUserList(HttpServletRequest request) {
        Map<String, String> paramsMap = RequestResponseUtil.getRequestParameters(request);
        int pageIndex = Integer.parseInt(paramsMap.get("pageIndex"));
        int pageSize = Integer.parseInt(paramsMap.get("pageSize"));
        PageHelper.startPage(pageIndex, pageSize);
        List<User> users = userService.getUserList();
        users.forEach(user->user.setPassword(null));
        PageInfo pageInfo = new PageInfo(users);
        return new Message().ok(RespCode.OK, "return user list success").addData(RespKey.PAGE_INFO, pageInfo);
    }

    @ApiOperation(value = "给用户授权添加角色",httpMethod = "POST")
    @PostMapping("/authority/role")
    public Message authorityUserRole(HttpServletRequest request) {
        Map<String,String> map = getRequestParameter(request);
        Long uid = Long.valueOf(map.get("uid"));
        int roleId = Integer.valueOf(map.get("roleId"));
        boolean flag = userService.authorityUserRole(uid,roleId);
        return flag ? new Message().ok(RespCode.OK,"authority success") : new Message().error(RespCode.ERROR,"authority error");
    }

    @ApiOperation(value = "删除已经授权的用户角色",httpMethod = "DELETE")
    @DeleteMapping("/authority/role/{uid}/{roleId}")
    public Message deleteAuthorityUserRole(@PathVariable Long uid, @PathVariable Integer roleId) {
        return userService.deleteAuthorityUserRole(uid,roleId) ? new Message().ok(RespCode.OK,"delete success") : new Message().error(RespCode.ERROR,"delete fail");
    }


//    @ApiOperation(value = "用户登出", httpMethod = "POST")
//    @PostMapping("/exit")
//    public Message accountExit(HttpServletRequest request) {
//        SecurityUtils.getSubject().logout();
//        Map<String,String > map = getRequestHeader(request);
//        String appId = map.get("appId");
//        if (StringUtils.isEmpty(appId)) {
//            return new Message().error(RespCode.ERROR, "用户未登录无法登出");
//        }
//        String jwt = redisTemplate.opsForValue().get(Constant.JWT_PREFIX+appId);
//        if (StringUtils.isEmpty(jwt)) {
//            return new Message().error(RespCode.ERROR, "用户未登录无法登出");
//        }
//        redisTemplate.opsForValue().getOperations().delete(Constant.JWT_PREFIX+appId);
//        LogExeManager.getInstance().executeLogTask(LogTaskFactory.exitLog(appId,request.getRemoteAddr(),(short)1,""));
//
//        return new Message().ok(RespCode.OK, "用户退出成功");
//    }


}
