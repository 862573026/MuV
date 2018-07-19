package com.newx.user.action;

import com.newx.annotation.Provider;
import com.newx.api.MaProvider;

/**
 * Created by xuzhijian on 2018/7/10 0010.
 */
@Provider(processName = "com.newx.muv.main:user")
public class UserProvider extends MaProvider {
    @Override
    protected String getName() {
        return "user";
    }
}
