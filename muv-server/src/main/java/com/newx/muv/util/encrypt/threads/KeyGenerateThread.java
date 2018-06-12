package com.newx.muv.util.encrypt.threads;

import java.security.KeyPair;


/**
 * Created by BullyBoo on 30.03.2017.
 */

public class KeyGenerateThread extends BaseThread<KeyPair> {

    public KeyGenerateThread(EncodeAction<KeyPair> encodeAction, ThreadCallback<KeyPair> threadCallback) {
        super(encodeAction, threadCallback);
    }
}
