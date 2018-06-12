package com.newx.service;

import android.app.Application;

/**
 * Created by newx on 18-4-26.
 */

public class ApiManager {

    private final ApiService apiService;

    private final Application application;

    public ApiManager(ApiService apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
    }



}
