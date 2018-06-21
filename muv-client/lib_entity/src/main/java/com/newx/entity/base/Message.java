package com.newx.entity.base;

import java.util.HashMap;
import java.util.Map;

/**
 * * @Author newx
 * @Description 前后端统一消息定义协议 Message  之后前后端数据交互都按照规定的类型进行交互
 * {
 *   code:
 *   message:
 *   timestamp:
 *   meta:{"code":code,“msg”:message}
 *   data:{....}
 * }
 * @Date 10:48 2018/2/14
 */
public class Message {

    private boolean success;
    private int code;
    private String message;
    private Long timestamp;

    // 消息内容  存储实体交互数据
    private Map<String,Object> data = new HashMap<String,Object>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
