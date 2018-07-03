package com.newx.muv.controller.file;

import com.newx.muv.service.FileService;
import com.newx.muv.util.RequestResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 上传组件 - 支持分片
 */
@Component
public class Uploader {

    @Autowired
    private FileService mFileService;

    /**
     * 普通上传
//     * @param name
//     * @param path
//     * @param md5
//     * @param file
     * @throws IOException
     */
//    public void upload(String name,
//                       String path,
//                       String md5,
//                       MultipartFile file) throws IOException {
//        mFileService.upload(name, path, md5, file);
//    }

    public int checkMd5(String md5) {
        return mFileService.checkMd5(md5);
    }

    /**
     * 分片上传
//     * @param name
//     * @param path
//     * @param md5
//     * @param size
//     * @param chunks
//     * @param chunk
//     * @param file
     * @throws IOException
     */
    public String upload(String path, HttpServletRequest request) throws IOException {
        Map<String, String> map = RequestResponseUtil.getRequestParameters(request);
        String name = map.get("name");
        String md5 = map.get("md5");
        long size = Long.parseLong(map.get("size"));
        int chunks = map.get("chunks") == null ? -1 : Integer.parseInt(map.get("chunks"));
        int chunk = map.get("chunk") == null ? -1 : Integer.parseInt(map.get("chunk"));
        MultiValueMap<String, MultipartFile> fileMap = RequestResponseUtil.getRequestMultiParameters(request);
        MultipartFile file = fileMap.get("file").get(0);
//        String path = request.getServletPath();

        if (chunks != -1 && chunks != 0) {
           return mFileService.uploadWithBlock(name, path, md5, size, chunks, chunk, file);
        } else {
            return mFileService.upload(name, path, md5, file);
        }
    }

    public boolean deleteByPath(String path) {
        return mFileService.deleteByPath(path);
    }
}
