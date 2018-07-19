package com.newx.api;

import android.content.Context;

import com.newx.api.router.MaActionResult;
import com.newx.api.router.RouterRequest;

/**
 * Created by wanglei on 2016/11/29.
 */

public interface MaAction<T> {
    boolean isAsync(Context context, RouterRequest<T> routerRequest);

    MaActionResult invoke(Context context, RouterRequest<T> routerRequest);

    String getName();
}
