package com.newx.muv.utils;

import android.support.annotation.StringRes;

import com.newx.base.proxy.ResourceProxy;
import com.newx.service.net.CommunicationService;

/**
 * Created by xuzhijian on 2018/5/31 0031.
 * URL 工厂
 */

public class UrlFactory {

    public static String base(@StringRes int urlRes) {
        return CommunicationService.getServerManager().getSelectedHttpServer() + ResourceProxy.getString(urlRes);
    }
}
