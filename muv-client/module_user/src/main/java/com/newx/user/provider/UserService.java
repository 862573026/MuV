package com.newx.user.provider;

import com.newx.common.frameworks.route.facade.template.IProvider;
import com.newx.user.entity.AuthInfo;
import com.newx.user.entity.User;

/**
 * Created by xuzhijian on 2018/7/10 0010.
 */

public interface UserService extends IProvider {

    boolean isLogin();

    User currentUser();

    void setOnlineStatus(AuthInfo authInfo, User loginUser);

    void setOfflineStatus();


}
