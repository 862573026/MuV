//package com.newx.muv.interceptor;
//
//
//import com.newx.entity.base.AuthInfo;
//import com.newx.muv.base.def.TAG;
//import com.newx.muv.mapper.AuthMapper;
//import com.newx.muv.utils.rx.rxbus.RxBus;
//
//import java.io.IOException;
//import java.util.TreeMap;
//
//import okhttp3.Request;
//import okhttp3.Response;
//
///**
// * Created by xuzhijian on 2018/5/11 0011.
// */
//
//public class SignInterceptor extends BaseDynamicInterceptor<SignInterceptor> {
//
//    private AuthMapper mAuthMapper = new AuthMapper();
//
//    private boolean authChanged = false;
//    private boolean loaded = false;
//    private String authorization = null;
//    private String uid = null;
//
//    @Override
//    public TreeMap<String, String> dynamic(TreeMap<String, String> dynamicMap) {
//        return dynamicMap;
//    }
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request.Builder builder = chain.request().newBuilder();
//
//        if (isAccessToken()) {
//            if (!loaded) {
//                RxBus.getInstance().register(TAG.EVENT_AUTHINFO_CHANGED, Boolean.class, changed -> authChanged = changed);
//                loaded = true;
//            }
//            if (authorization == null || uid == null || authChanged) {
//
//                AuthInfo authInfo = mAuthMapper.loadFirst();
//
//                authorization = authInfo.getAuthorization();
//                uid = authInfo.getUid();
//                authChanged = false;
//            }
//            builder.header("authorization", authorization).build();
//            builder.header("uid", uid).build();
//
//        }
//
//        return chain.proceed(builder.build());
//    }
//
//}
