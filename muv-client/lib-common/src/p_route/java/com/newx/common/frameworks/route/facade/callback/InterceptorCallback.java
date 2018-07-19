package com.newx.common.frameworks.route.facade.callback;

import com.newx.common.frameworks.route.facade.Postcard;

/**
 * The callback of interceptor.
 *
 * @author Alex <a href="mailto:zhilong.liu@aliyun.com">Contact me.</a>
 * @version 1.0
 * @since 16/8/4 17:36
 */
public interface InterceptorCallback {

    /**
     * Continue process
     *
     * @param postcard com.newx.common.frameworks.route meta
     */
    void onContinue(Postcard postcard);

    /**
     * Interrupt process, pipeline will be destory when this method called.
     *
     * @param exception Reson of interrupt.
     */
    void onInterrupt(Throwable exception);
}
