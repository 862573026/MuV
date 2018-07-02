package com.newx.muv.util;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 分块上传工具类
 */
public class UploadUtils {

    public final static String KEY_NAME = "name";
    public final static String KEY_MD5 = "md5";
    public final static String KEY_SIZE = "size";
    public final static String KEY_CHUNKS = "chunks";
    public final static String KEY_CHUNK = "chunk";
    public final static String KEY_FILE = "file";
    public final static String KEY_PATH = "path";

    public static Map<String,Object> uploadParam(ServletRequest request){
        Map<String,Object> param = new HashMap<>();
        String relativePath = ((HttpServletRequest) request).getServletPath();
        param.putAll(RequestResponseUtil.getRequestParameters(request));
        param.putAll(RequestResponseUtil.getRequestMultiParameters(request));
        param.put(KEY_PATH,relativePath);
        return param;
    }
}
