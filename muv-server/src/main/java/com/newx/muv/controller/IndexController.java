package com.newx.muv.controller;

import com.newx.muv.common.RespCode;
import com.newx.muv.entity.vo.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Xuzhijian on 2018/5/28 0028.
 * 首页返回
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public Message index() {
         return new Message().ok(RespCode.OK, "Welcome to MuV !");
    }
}
