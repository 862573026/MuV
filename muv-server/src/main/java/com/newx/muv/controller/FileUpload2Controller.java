package com.newx.muv.controller;

import com.newx.muv.entity.vo.Message;
import com.newx.muv.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/upload")
public class FileUpload2Controller {

    @Autowired
    private FileService mFileService;

    @PostMapping("/standard")
    public void upload(String name,
                       String relativePath,
                       String md5,
                       MultipartFile file) throws IOException {
        mFileService.upload(name, relativePath, md5, file);
    }

    @GetMapping("/checkMd5")
    public Message upload(String md5) {
        return mFileService.checkMd5(md5);
    }

    @PostMapping("/chunk")
    public void upload(ServletRequest request,
                       String name,
                       String md5,
                       Long size,
                       Integer chunks,
                       Integer chunk,
                       MultipartFile file) throws IOException {
        String relativePath = ((HttpServletRequest) request).getServletPath();
        if (chunks != null && chunks != 0) {
            mFileService.uploadWithBlock(name, relativePath, md5, size, chunks, chunk, file);
        } else {
            mFileService.upload(name, relativePath, md5, file);
        }
    }
}
