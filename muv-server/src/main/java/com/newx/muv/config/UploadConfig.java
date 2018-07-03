package com.newx.muv.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfig {

    public static String path;

    public static String apkPath;

    @Value("${upload.path}")
    public void setPath(String path) {
        UploadConfig.path = path;
    }

    @Value("${upload.apk-path}")
    public void setApkPath(String apkPath) {
        UploadConfig.apkPath = apkPath;
    }
}
