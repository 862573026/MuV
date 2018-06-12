package com.newx.muv.vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.base.frameworks.util.log.NXLog;
import com.newx.base.ui.fragmentation.SupportFragmentDelegate;
import com.newx.base.utils.encrypt.Encoder;
import com.newx.base.utils.text.GsonUtil;
import com.newx.entity.LoginResult;
import com.newx.entity.base.AuthInfo;
import com.newx.entity.base.Message;
import com.newx.entity.base.User;
import com.newx.muv.base.def.TAG;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.helper.LoginHelper;
import com.newx.muv.http.Http;
import com.newx.muv.ioc.util.InjectConfig;
import com.newx.muv.view.route.ACTIVITY;
import com.newx.muv.proxy.MiddlewareProxy;
import com.newx.muv.utils.dialog.DialogBuilderHelper;
import com.newx.muv.utils.rx.rxbus.RxBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xuzhijian on 2018/5/3 0003.
 */

public class LoginByPswVM extends NSBaseVM {


    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    private Context mContext;
//    private AuthMapper mAuthMapper = new AuthMapper();
//    private UserMapper mUserMapper = new UserMapper();

    private LoginHelper mLoginHelper = new LoginHelper();


    String authorization = null;
    String uid = null;


    @Inject
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
                            if (message.getMeta().isSuccess()) {

                            }

                            String userKey = GsonUtil.toJsonObject(response)
                                    .getAsJsonObject("data")
                                    .getAsJsonPrimitive("userKey")
                                    .getAsString();

                            String tokenKey = GsonUtil.toJsonObject(response)
                                    .getAsJsonObject("data")
                                    .getAsJsonPrimitive("tokenKey")
                                    .getAsString();

                            String timestamp = GsonUtil.toJsonObject(response)
                                    .getAsJsonObject("meta")
                                    .getAsJsonPrimitive("timestamp")
                                    .getAsString();
                            loginRequest(timestamp, userKey, tokenKey);
                        },
                        throwable -> DialogBuilderHelper.showTipDialog(mContext, throwable.getMessage()));
    }


    public void loginRequest(String timestamp, String userKey, String tokenKey) {

        String pswAes = Encoder.BuilderAES()
                .method(com.newx.base.utils.encrypt.methods.AES.Method.AES_CBC_PKCS5PADDING)
                .message(password.get())
                .key(tokenKey)
                .encrypt();

        Http.getApi().loginByPsw(username.get(), timestamp, "login", userKey, pswAes)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                            Message message = GsonUtil.toEntity(response, Message.class);
                            if (message.getMeta().isSuccess()) {
                                LoginResult loginResult = GsonUtil.toEntity(GsonUtil.toJson(message.getData()),
                                        LoginResult.class);
                                uid = loginResult.getUser().getUid();
                                authorization = loginResult.getJwt();
                                AuthInfo authInfo = new AuthInfo();
                                authInfo.setUid(uid);
                                authInfo.setAuthorization(authorization);

                                User loginUser = loginResult.getUser();

                                mLoginHelper.setOnlineStatus(authInfo, loginUser);

                                RxBus.getInstance().postSticky(TAG.EVENT_AUTHINFO_CHANGED, true);
                                MiddlewareProxy.getRuntimeDataManager().currentUser = loginUser;

                                ARouter.getInstance()
                                        .build(ACTIVITY.TabActivity)
                                        .navigation();
                                exit(mContext);
                            } else {
                                DialogBuilderHelper.showTipDialog(mContext, message.getMeta().getMsg());
                            }
                        },
                        throwable -> DialogBuilderHelper.showTipDialog(mContext, throwable.getMessage()));
    }

    @Override
    public InjectConfig injectConfig() {
        return InjectConfig.builder()
                .needClientInject(true)
                .build();
    }
}
