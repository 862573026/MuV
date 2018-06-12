package com.newx.muv.http;


import java.io.File;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by xuzhijian on 2018/6/6 0006.
 */

public interface Api {

    /**
     * 获取tokenKey
     *
     * @param query
     * @return
     */
    @GET("account")
    Observable<String> tokenKey(@Query("tokenKey") String query);

    /**
     * 密码登录
     *
     * @param appId
     * @param timestamp
     * @param methodName
     * @param userKey
     * @param password
     * @return
     */
    @POST("/account/login")
    Observable<String> loginByPsw(@Query("appId") String appId,
                                  @Query("timestamp") String timestamp,
                                  @Query("methodName") String methodName,
                                  @Query("userKey") String userKey,
                                  @Query("password") String password);

    @POST("/account/register")
    Observable<String> registerByPsw(@Query("username") String username,
                                     @Query("password") String password,
                                     @Query("userKey") String userKey,
                                     @Query("timestamp") String timestamp);

    @POST("/upload/checkFileMd5")
    Observable<String> checkFileMd5(@Query("md5") String fileMd5);

    @Multipart
    @POST("/upload/fileUpload")
    Observable<String> fileUpload(@Query("uid") String uid,
                                  @Query("id") String taskId,
                                  @Query("name") String filename,
                                  @Query("chunks") int chunks,
                                  @Query("chunk") int chunk,
                                  @Query("size") long size,
                                  @Query("md5") String md5,
                                  @Part MultipartBody.Part file);
}
