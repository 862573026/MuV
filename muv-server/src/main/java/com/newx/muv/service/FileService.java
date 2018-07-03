package com.newx.muv.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    public String upload(String name,
                       String relativePath,
                       String md5,
                       MultipartFile file) throws IOException;

    public String uploadWithBlock(String name,
                                String relativePath,
                                String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                MultipartFile file) throws IOException;

    public int checkMd5(String md5);

    public boolean deleteByPath(String path);

}
