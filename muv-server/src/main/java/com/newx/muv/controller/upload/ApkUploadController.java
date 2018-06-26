package com.newx.muv.controller.upload;

import com.newx.muv.controller.BasicAction;
import com.newx.muv.entity.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by xuzhijian on 2018/6/26 0026.
 * Apk上传的Controller
 */
@RestController
@RequestMapping(value = "/upload/apk")
public class ApkUploadController extends BasicAction {

    @Autowired
    HttpUpload mHttpUpload;

    /**
     * 秒传判断，断点判断
     *
     * @return
     */
    @PostMapping(path = "/checkFileMd5")
    public Message checkFileMd5(HttpServletRequest request) throws IOException {
        return mHttpUpload.checkFileMd5(request);
    }


    @PostMapping(path = "/fileUpload")
    public Message fileUpload(HttpServletRequest request) {
        return mHttpUpload.fileUpload(request,"apk");
    }
}
