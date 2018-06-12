package com.newx.service.http;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by xuzhijian on 2018/6/11 0011.
 * Http 抽象类
 */

public interface HttpImp {

    //使用 RetrofitUrlManager 管理BaseUrl的动态切换
    OkHttpClient okHttpClient = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder()).build();
    //解决参数多了双引号的问题
    Converter.Factory stringConverterFactory = ScalarsConverterFactory.create();
    Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

}
