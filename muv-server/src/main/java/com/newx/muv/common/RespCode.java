package com.newx.muv.common;

/**
 * 响应Code
 */
public class RespCode {

    public final static int OK = 200;
    public final static int ERROR = 444;

    public final static int JWT_AUTH_OK = 1000;

    public final static int UPLOAD_NO_FILE = 2000;    // 不存在这个文件
    public final static int UPLOAD_FILE_EXIST = 2001; // 已存在存在这个文件
    public final static int UPLOAD_HAVE_FILE = 2002; // 存在一部分


}
