package com.newx.muv.entity.vo;

import com.newx.muv.common.RespCode;
import com.newx.muv.common.RespMsg;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author newx
 * @Description 前后端统一消息定义协议 Message  之后前后端数据交互都按照规定的类型进行交互
 * {
 * code:
 * message:
 * timestamp:
 * meta:{"code":code,“msg”:message}
 * data:{....}
 * }
 * @Date 10:48 2018/2/14
 */
public class Message {

    private boolean success;
    private int code;
    private String message;
    private Long timestamp;

    // 消息内容  存储实体交互数据
    private Map<String, Object> data = new HashMap<String, Object>();

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

    public Message setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public Message addData(String key, Object object) {
        this.data.put(key, object);
        return this;
    }

    public Message addData(String key, Integer object) {
        this.data.put(key, object);
        return this;
    }

    /**
     * 成功信息 简单返回
     * @return
     */
    public Message ok() {
        this.success = Boolean.TRUE;
        this.code = RespCode.OK;
        this.message = RespMsg.SUCCESS;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    /**
     * 错误信息 简单返回
     * @return
     */
    public Message error() {
        this.success = Boolean.TRUE;
        this.code = RespCode.ERROR;
        this.message = RespMsg.ERROR;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public Message ok(int statusCode, String statusMsg) {
        this.success = Boolean.TRUE;
        this.code = statusCode;
        this.message = statusMsg;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public Message error(int statusCode, String statusMsg) {
        this.success = Boolean.FALSE;
        this.code = statusCode;
        this.message = statusMsg;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

}
