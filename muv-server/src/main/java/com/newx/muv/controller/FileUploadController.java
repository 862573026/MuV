package com.newx.muv.controller;

import com.newx.muv.entity.vo.Message;
import com.newx.muv.util.FileIOUtils;
import com.newx.muv.util.FileUtils;
import com.newx.muv.util.RequestResponseUtil;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by xuzhijian on 2018/5/29 0029.
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController extends BasicAction {
    public String getUploadDir(String dirName) {
        try {
            return ResourceUtils.getURL(dirName).getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 简单的上传
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Message simpleUpload(HttpServletRequest request) {
        MultipartFile file = RequestResponseUtil.getRequestMultiParameters(request, "file");
        if (!file.isEmpty()) {
            //上传目录地址
            String fileDir = getUploadDir( "file");
            //如果目录不存在，自动创建文件夹
            FileUtils.createOrExistsDir(fileDir);
            //上传的文件名
            String fileName = file.getOriginalFilename();
            //创建服务端保存的对象
            boolean createServerFile = FileUtils.createFileByDeleteOldFile(fileDir + fileName);

            try {
                if (createServerFile) {
                    file.transferTo(FileUtils.getFileByPath(fileDir + fileName));
                }
            } catch (IOException e) {
                e.printStackTrace();
                return new Message().error(201, "上传失败");
            }
            return new Message().ok(200, "上传成功");
        }
        return new Message().error(202, "文件不能为空");
    }


    /**
     * @param guid             临时文件名
     * @param md5value         客户端生成md5值
     * @param chunks           分块数
     * @param chunk            分块序号
     * @param id               文件id便于区分
     * @param name             上传文件名
     * @param type             文件类型
     * @param lastModifiedDate 上次修改时间
     * @param size             文件大小
     * @param file             文件本身
     * @return
     */
    @RequestMapping(value = "/bigFile")
    public String fileUpload(String guid,
                             String md5value,
                             String chunks,
                             String chunk,
                             String id,
                             String name,
                             String type,
                             String lastModifiedDate,
                             int size,
                             MultipartFile file) {
        String fileName;
        try {
            int index;
            String uploadFolderPath = getUploadDir("bigFile");

            String mergePath =uploadFolderPath + guid + "/";
            String ext = name.substring(name.lastIndexOf("."));

            //判断文件是否分块
            if (chunks != null && chunk != null) {
                index = Integer.parseInt(chunk);
                fileName = String.valueOf(index) + ext;
                // 将文件分块保存到临时文件夹里，便于之后的合并文件
                file.transferTo(FileUtils.getFileByPath(mergePath + fileName));
                // 验证所有分块是否上传成功，成功的话进行合并
//                Uploaded(md5value, guid, chunk, chunks, uploadFolderPath, fileName, ext, fileService);
            } else {
                fileName = guid + ext;
                //上传文件没有分块的话就直接保存
                file.transferTo(FileUtils.getFileByPath(uploadFolderPath + fileName));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"error\":true}";
        }

        return "{jsonrpc = \"2.0\",id = id,filePath = \"/Upload/\" + fileFullName}";
    }


}
