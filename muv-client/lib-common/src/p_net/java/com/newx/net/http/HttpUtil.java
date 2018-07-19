package com.newx.net.http;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * Created by xuzhijian on 2018/5/11 0011.
 */

public class HttpUtil {

    public static void setBaseURL(String ip, int port) {
        RetrofitUrlManager.getInstance().setGlobalDomain(ip + ":" + port);
    }

    public static void setBaseURL(String url) {
        RetrofitUrlManager.getInstance().setGlobalDomain(url);
    }
}
