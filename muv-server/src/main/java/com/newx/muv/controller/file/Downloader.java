package com.newx.muv.controller.file;

import com.newx.muv.config.UploadConfig;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by xuzhijian on 2018/7/3 0003.
 * 下载组件
 */
@Component
public class Downloader {

    //文件下载相关代码
    public boolean downloadFile(String path, String sourceName,String fileName, HttpServletResponse response) {
        //设置文件路径
        File file = new File(UploadConfig.path + path, sourceName);
        if (!file.exists()) {
            return false;
        }

        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            System.out.println("success");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
