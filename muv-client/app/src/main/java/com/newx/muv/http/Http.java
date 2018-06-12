package com.newx.muv.http;

import com.newx.service.http.HttpImp;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by xuzhijian on 2018/6/6 0006.
 * Http入口
 */

public class Http implements HttpImp{

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
