package com.newx.muv.vm;

import android.content.Context;
import android.databinding.ObservableField;

import com.newx.base.utils.encrypt.Encoder;
import com.newx.base.utils.text.GsonUtil;
import com.newx.entity.base.Message;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.http.Http;
import com.newx.muv.utils.dialog.DialogBuilderHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xuzhijian on 2018/5/4 0004.
 */

public class RegisterVM extends NSBaseVM {

    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> passwordConfirm = new ObservableField<>();

    Context mContext;

    public RegisterVM(Context context) {
        mContext = context;
    }

    public void register() {
        getTokenKeyRequest();
    }

    public void getTokenKeyRequest() {
        Http.getApi().tokenKey("get")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                            Message message = GsonUtil.toEntity(response, Message.class);

                            String userKey = (String) message.getData().get("userKey");
                            String tokenKey = (String) message.getData().get("tokenKey");
                            String timestamp = message.getTimestamp().toString();

                            registerRequest(timestamp, userKey, tokenKey);
                        },
                        throwable -> DialogBuilderHelper.showTipDialog(mContext, throwable.getMessage()));
    }

    public void registerRequest(String timestamp, String userKey, String tokenKey) {

        String pswAes = Encoder.BuilderAES()
                .method(com.newx.base.utils.encrypt.methods.AES.Method.AES_CBC_PKCS5PADDING)
                .message(password.get())
                .key(tokenKey)
                .encrypt();

        Http.getApi().registerByPsw(username.get(), pswAes, userKey, timestamp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                            Message message = GsonUtil.toEntity(response, Message.class);
                            DialogBuilderHelper.showTipDialog(mContext, message.getMessage());
                        },
                        throwable -> DialogBuilderHelper.showTipDialog(mContext, throwable.getMessage()));
    }
}
