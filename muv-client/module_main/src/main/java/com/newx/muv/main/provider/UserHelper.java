package com.newx.muv.main.provider;

import com.newx.common.frameworks.route.launcher.ARouter;
import com.newx.user.entity.User;
import com.newx.user.provider.UserService;

/**
 * Created by xuzhijian on 2018/7/10 0010.
 */

public class UserHelper {

    public static boolean isLogin(){
        return ARouter.getInstance().navigation(UserService.class).isLogin();
    }

    public static User currentUser(){
        return ARouter.getInstance().navigation(UserService.class).currentUser();
    }
}
