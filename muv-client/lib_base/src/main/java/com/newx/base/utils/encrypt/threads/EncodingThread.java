package com.newx.base.utils.encrypt.threads;

/**
 * Created by BullyBoo on 29.03.2017.
 */

public class EncodingThread extends BaseThread<String>{

    public EncodingThread(EncodeAction<String> encodeAction, ThreadCallback<String> threadCallback) {
        super(encodeAction, threadCallback);
    }
}
