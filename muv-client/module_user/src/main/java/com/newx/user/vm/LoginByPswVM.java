package com.newx.user.vm;

import android.content.Context;
import android.databinding.ObservableField;


import com.newx.base.route.RP;
import com.newx.base.vm.NxBaseVM;
import com.newx.common.frameworks.route.launcher.ARouter;
import com.newx.common.helper.DialogBuilderHelper;
import com.newx.net.entity.Message;
import com.newx.user.entity.AuthInfo;
import com.newx.user.entity.LoginResult;
import com.newx.user.entity.User;
import com.newx.user.helper.LoginHelper;
import com.newx.user.http.Http;
import com.newx.utils.encrypt.Encoder;
import com.newx.utils.encrypt.methods.AES;
import com.newx.utils.text.GsonUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xuzhijian on 2018/5/3 0003.
 */

public class LoginByPswVM extends NxBaseVM {


    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    private Context mContext;

    private LoginHelper mLoginHelper = new LoginHelper();


    String authorization = null;
    String uid = null;


    public LoginByPswVM(Context context) {
        mContext = context;
    }


    public void login() {

        getTokenKeyRequest();
    }

    public void getTokenKeyRequest() {
        Http.getApi().tokenKey("get")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                            Message message = GsonUtil.toEntity(response, Message.class);
                            if (!message.isSuccess()) {
                                return;
                            }

                            String userKey = (String) message.getData().get("userKey");
                            String tokenKey = (String) message.getData().get("tokenKey");
                            String timestamp = message.getTimestamp().toString();
                            loginRequest(timestamp, userKey, tokenKey);
                        },
                        throwable -> DialogBuilderHelper.showTipDialog(mContext, throwable.getMessage()));
    }


    public void loginRequest(String timestamp, String userKey, String tokenKey) {

        String pswAes = Encoder.BuilderAES()
                .method(AES.Method.AES_CBC_PKCS5PADDING)
                .message(password.get())
                .key(tokenKey)
                .encrypt();

        Http.getApi().loginByPsw(username.get(), timestamp, "login", userKey, pswAes)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                            Message message = GsonUtil.toEntity(response, Message.class);
                            if (message.isSuccess()) {
                                LoginResult loginResult = GsonUtil.toEntity(GsonUtil.toJson(message.getData()),
                                        LoginResult.class);
                                uid = loginResult.getUser().getUid();
                                authorization = loginResult.getJwt();
                                AuthInfo authInfo = new AuthInfo();
                                authInfo.setUid(uid);
                                authInfo.setAuthorization(authorization);

                                User loginUser = loginResult.getUser();

                                mLoginHelper.setOnlineStatus(authInfo, loginUser);

//                                MiddlewareProxy.getRuntimeDataManager().currentUser = loginUser;

                                ARouter.getInstance()
                                        .build(RP.TabActivity)
                                        .navigation();
                                exit(mContext);
                            } else {
                                DialogBuilderHelper.showTipDialog(mContext, message.getMessage());
                            }
                        },
                        throwable -> DialogBuilderHelper.showTipDialog(mContext, throwable.getMessage()));
    }


}
