package com.newx.muv.controller.file;

import com.newx.muv.config.UploadConfig;
import com.newx.muv.entity.vo.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xuzhijian on 2018/7/3 0003.
 */
@RestController
@RequestMapping("/download")
public class DownloadController {
    @Autowired
    Downloader mDownloader;

    @ApiOperation(value = "下载Apk", httpMethod = "GET")
    @GetMapping("apk/{sourceName}/{fileName}")
    public Message downloadApk(@PathVariable String sourceName, @PathVariable String fileName, HttpServletResponse response) throws IOException {
        if (mDownloader.downloadFile(UploadConfig.apkPath, sourceName, fileName, response)) {
            return null;
        } else {
            return new Message().error();
        }
    }
}
