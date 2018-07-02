package com.newx.muv.controller.upload;

import com.newx.muv.controller.BasicAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.io.IOException;

/**
 * Created by xuzhijian on 2018/6/26 0026.
 * 文件上传
 */
@RestController
@RequestMapping(value = "/upload/file")
public class FileUploadController extends BasicAction {

    @Autowired
    Uploader mUploader;

    @GetMapping("/checkMd5")
    public int checkMd5(String md5) {
        return mUploader.checkMd5(md5);
    }

    @PostMapping("/")
    public void chunkUpload(ServletRequest request) throws IOException {
        mUploader.upload(request);
    }
}
