package com.newx.service.utils;


//import com.newx.service.http.EasyHttp;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * Created by xuzhijian on 2018/5/11 0011.
 */

public class HttpUtil {

    public static void setBaseURL(String ip, int port) {
//        EasyHttp.getInstance().setBaseUrl(ip + ":" + port);
        RetrofitUrlManager.getInstance().setGlobalDomain(ip + ":" + port);
    }

    public static void setBaseURL(String url) {
//        EasyHttp.getInstance().setBaseUrl(url);
        RetrofitUrlManager.getInstance().setGlobalDomain(url);
    }
}
