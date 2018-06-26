package com.newx.muv.service;


import javax.servlet.ServletRequest;
import java.io.IOException;

/**
 * 存储操作的service
 * Created by 超文 on 2017/5/2.
 */
public interface StorageService {

    /**
     * 删除全部数据
     */
    void deleteAll();

    /**
     * 初始化方法
     */
    void init();


    /**
     * 上传文件方法2
     * 处理文件分块，基于MappedByteBuffer来实现文件的保存
     *
     * @param request
     * @throws IOException
     */

    void uploadFileByMappedByteBuffer(ServletRequest request, String uploadDir) throws IOException;

}
