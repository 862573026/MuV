package com.newx.muv.common;

/**
 * 响应Code
 */
public class RespCode {

    public final static int OK = 200;
    public final static int ERROR = 444;

    public final static int JWT_AUTH_OK = 1000;

    public final static int UPLOAD_FILE_ERROR = -2000; // 上传错误
    public final static int UPLOAD_NO_FILE = -2001;    // 不存在这个文件
    public final static int UPLOAD_FILE_EXIST = -2002; // 已存在这个文件
    public final static int UPLOAD_HAVE_FILE = -2003; // 存在一部分


}
