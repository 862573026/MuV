package com.newx.common.frameworks.route.facade.service;

import android.content.Context;

import com.newx.common.frameworks.route.facade.Postcard;
import com.newx.common.frameworks.route.facade.template.IProvider;

/**
 * Provide degrade service for router, you can do something when com.newx.common.frameworks.route has lost.
 *
 * @author Alex <a href="mailto:zhilong.liu@aliyun.com">Contact me.</a>
 * @version 1.0
 * @since 2016/9/22 14:51
 */
public interface DegradeService extends IProvider {

    /**
     * Router has lost.
     *
     * @param postcard meta
     */
    void onLost(Context context, Postcard postcard);
}
