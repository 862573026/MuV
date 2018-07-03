package com.newx.muv.controller.file;

import com.newx.muv.config.UploadConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by xuzhijian on 2018/7/3 0003.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    Uploader mUploader;

    @ApiOperation(value = "获取文件的上传状态", httpMethod = "GET")
    @GetMapping("/checkMd5")
    public int checkMd5(String md5) {
        return mUploader.checkMd5(md5);
    }


    @ApiOperation(value = "上传文件", httpMethod = "POST")
    @PostMapping("/apk")
    public String uploadApk(HttpServletRequest request) throws IOException {
        return mUploader.upload(UploadConfig.apkPath, request);
    }
}
