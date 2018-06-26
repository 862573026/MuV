package com.newx.muv.controller;

import com.newx.muv.common.RespCode;
import com.newx.muv.entity.vo.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by newx on 2018/5/9 0009.
 */
@RestController
public class AuthController {
    @ApiOperation(value = "jwt验证", notes = "jwt验证")
    @PostMapping("/jwt/auth")
    public Message auth() {
        return new Message().ok(RespCode.JWT_AUTH_OK, "jwt权限验证成功");
    }
}
