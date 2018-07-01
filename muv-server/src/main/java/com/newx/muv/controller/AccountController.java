package com.newx.muv.controller;

import com.newx.muv.common.Constant;
import com.newx.muv.common.DefKey;
import com.newx.muv.common.RespCode;
import com.newx.muv.entity.bo.User;
import com.newx.muv.entity.bo.UserRoleInfo;
import com.newx.muv.entity.vo.Message;
import com.newx.muv.service.AccountService;
import com.newx.muv.service.UserService;
import com.newx.muv.support.factory.LogTaskFactory;
import com.newx.muv.support.manager.LogExeManager;
import com.newx.muv.util.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* *
 * @Author newx
 * @Description post新增,get读取,put完整更新,patch部分更新,delete删除
 * @Date 14:40 2018/3/8
 */
@RestController
@RequestMapping("/account")
public class AccountController extends BasicAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    /* *
     * @Description 这里已经在 passwordFilter 进行了登录认证
     * @Param [] 登录签发 JWT
     * @Return java.lang.String
     */
    @ApiOperation(value = "用户登录", notes = "POST用户登录签发JWT")
    @PostMapping("/login")
    public Message accountLogin(HttpServletRequest request) {
        Map<String, String> params = RequestResponseUtil.getRequestParameters(request);
        String appId = params.get("appId");
        // 根据appId获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
        String roles = accountService.loadAccountRole(appId);
        // 时间以秒计算,token有效刷新时间是token有效过期时间的2倍
        long refreshPeriodTime = 36000L;
        String jwt = JsonWebTokenUtil.issueJWT(UUID.randomUUID().toString(), appId,
                "token-server", refreshPeriodTime >> 2, roles, null, SignatureAlgorithm.HS512);
        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
        User user = userService.getUserByAppId(appId);
        redisTemplate.opsForValue().set(DefKey.JWT_PREFIX + user.getUid(), jwt, refreshPeriodTime, TimeUnit.SECONDS);
        user.setPassword(null);
        user.setSalt(null);

        LogExeManager.getInstance().executeLogTask(LogTaskFactory.loginLog(user.getUid(), IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "登录成功"));

        return new Message().ok(RespCode.OK, "issue jwt success")
                .addData("jwt", jwt)
                .addData("user", user);
    }

    /* *
     * @Description 用户账号的注册
     * @Param [request, response]
     * @Return Message
     */
    @ApiOperation(value = "用户注册", notes = "POST用户注册")
    @PostMapping("/register")
    public Message accountRegister(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> params = RequestResponseUtil.getRequestParameters(request);
        User user = new User();
        String username = params.get("username");
        String password = params.get("password");
        String userKey = params.get("userKey");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error(RespCode.ERROR, "账户信息缺失");
        }
        if (accountService.isAccountExistByUsername(username)) {
            // 账户已存在
            return new Message().error(RespCode.ERROR, "账户已存在");
        }

        user.setUsername(username);

        // 从Redis取出密码传输加密解密秘钥
        String tokenKey = redisTemplate.opsForValue().get("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase() + userKey);
        String realPassword = AESUtil.aesDecode(password, tokenKey);
        String salt = CommonUtil.getRandomString(6);
        // 存储到数据库的密码为 MD5(原密码+盐值)
        user.setPassword(MD5Util.md5(realPassword + salt));
        user.setSalt(salt);
        user.setCreateTime(new Date());
        if (!StringUtils.isEmpty(params.get("username"))) {
            user.setUsername(params.get("username"));
        }
        if (!StringUtils.isEmpty(params.get("realName"))) {
            user.setRealName(params.get("realName"));
        }
        if (!StringUtils.isEmpty(params.get("avatar"))) {
            user.setAvatar(params.get("avatar"));
        }
        if (!StringUtils.isEmpty(params.get("phone"))) {
            user.setPhone(params.get("phone"));
        }
        if (!StringUtils.isEmpty(params.get("email"))) {
            user.setEmail(params.get("email"));
        }
        if (!StringUtils.isEmpty(params.get("sex"))) {
            user.setSex(Byte.valueOf(params.get("sex")));
        }
        if (!StringUtils.isEmpty(params.get("createWhere"))) {
            user.setCreateWhere(Byte.valueOf(params.get("createWhere")));
        }
        user.setStatus((byte) 1);

        if (accountService.registerAccount(user)) {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(username, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "注册成功"));
            return new Message().ok(2002, "注册成功");
        } else {
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(username, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
            return new Message().ok(RespCode.ERROR, "注册失败");
        }
    }

    @ApiOperation(value = "用户登出", httpMethod = "POST")
    @PostMapping("/logout")
    public Message logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        Map<String, String> map = getRequestHeader(request);
        String uid = map.get("uid");
        if (StringUtils.isEmpty(uid)) {
            return new Message().error(RespCode.ERROR, "用户未登录无法登出");
        }
        String jwt = redisTemplate.opsForValue().get(DefKey.JWT_PREFIX + uid);
        if (StringUtils.isEmpty(jwt)) {
            return new Message().error(RespCode.ERROR, "用户未登录无法登出");
        }
        redisTemplate.opsForValue().getOperations().delete(DefKey.JWT_PREFIX + uid);
        LogExeManager.getInstance().executeLogTask(LogTaskFactory.exitLog(uid, request.getRemoteAddr(), (short) 1, ""));

        return new Message().ok(RespCode.OK, "用户退出成功");
    }

}
