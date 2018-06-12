package com.newx.entity.base;

import com.newx.entity.LoginResult;
import com.newx.entity.def.INVALID;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* *
 * @Author newx
 * @Description 前后端统一消息定义协议 Message  之后前后端数据交互都按照规定的类型进行交互
 * {
 *   meta:{"code":code,“msg”:message}
 *   data:{....}
 * }
 * @Date 10:48 2018/2/14
 */
public class Message {

    // 消息头meta 存放状态信息 code message
//    private Map<String, Object> meta = new HashMap<String, Object>();
    private Meta meta;
    // 消息内容  存储实体交互数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }


    public Map<String, Object> getData() {
        return data;
    }

    public Message setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}
