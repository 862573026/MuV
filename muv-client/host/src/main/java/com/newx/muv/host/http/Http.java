package com.newx.muv.host.http;

import com.newx.service.http.HttpImp;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import retrofit2.Retrofit;

/**
 * Created by xuzhijian on 2018/6/7 0007.
 */

public class Http implements HttpImp {

    private static HostApi api;

    public static HostApi getHostApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(RetrofitUrlManager.getInstance().getGlobalDomain())
                    .addConverterFactory(stringConverterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            api = retrofit.create(HostApi.class);
        }
        return api;
    }
}
