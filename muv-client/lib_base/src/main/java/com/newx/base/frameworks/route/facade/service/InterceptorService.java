package com.newx.base.frameworks.route.facade.service;

import com.newx.base.frameworks.route.facade.Postcard;
import com.newx.base.frameworks.route.facade.callback.InterceptorCallback;
import com.newx.base.frameworks.route.facade.template.IProvider;

/**
 * Interceptor service
 *
 * @author zhilong <a href="mailto:zhilong.lzl@alibaba-inc.com">Contact me.</a>
 * @version 1.0
 * @since 2017/2/23 下午2:06
 */
public interface InterceptorService extends IProvider {

    /**
     * Do interceptions
     */
    void doInterceptions(Postcard postcard, InterceptorCallback callback);
}
