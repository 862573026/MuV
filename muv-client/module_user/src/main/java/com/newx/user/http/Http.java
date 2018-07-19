package com.newx.user.http;


import com.newx.net.http.HttpImp;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import retrofit2.Retrofit;

/**
 * Created by xuzhijian on 2018/6/6 0006.
 * Http入口
 */

public class Http implements HttpImp {

    private static Api api;

    public static Api getApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(RetrofitUrlManager.getInstance().getGlobalDomain())
                    .addConverterFactory(stringConverterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }

}
