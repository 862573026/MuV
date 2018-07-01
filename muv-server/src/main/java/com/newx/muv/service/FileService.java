package com.newx.muv.service;

import com.newx.muv.entity.vo.Message;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    public void upload(String name,
                       String relativePath,
                       String md5,
                       MultipartFile file) throws IOException;

    public void uploadWithBlock(String name,
                                String relativePath,
                                String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                MultipartFile file) throws IOException;

    public Message checkMd5(String md5);

}
