package com.newx.muv.controller.file;

import com.newx.muv.config.UploadConfig;
import com.newx.muv.util.FileUtils;

/**
 * Created by xuzhijian on 2018/7/3 0003.
 * 删除文件
 */
public class DeleteUtil {

    public static final boolean deleteFile(String filePath) {
        String path = UploadConfig.path + filePath;
        if (FileUtils.isFileExists(path)) {
            return FileUtils.deleteFile(path);
        }else {
            return false;
        }
    }
}
