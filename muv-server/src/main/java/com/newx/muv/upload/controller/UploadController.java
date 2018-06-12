package com.newx.muv.upload.controller;

import com.newx.muv.common.RespCode;
import com.newx.muv.controller.BasicAction;
import com.newx.muv.entity.vo.Message;
import com.newx.muv.upload.param.MultipartFileParam;
import com.newx.muv.upload.service.StorageService;
import com.newx.muv.upload.utils.Constants;
import com.newx.muv.upload.vo.ResultStatus;
import com.newx.muv.upload.vo.ResultVo;
import com.newx.muv.util.RequestResponseUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 默认控制层
 * Created by wenwen on 2017/4/11.
 * version 1.0
 */
@RestController
@RequestMapping(value = "/upload")
public class UploadController extends BasicAction{

    private Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private StorageService storageService;

    /**
     * 秒传判断，断点判断
     *
     * @return
     */
    @PostMapping(value = "checkFileMd5")
    public Message checkFileMd5(HttpServletRequest request) throws IOException {
        String md5 = RequestResponseUtil.getParameter(request, "md5");
        Object processingObj = redisTemplate.opsForHash().get(Constants.FILE_UPLOAD_STATUS, md5);
        if (processingObj == null) {
            return new Message().ok(RespCode.UPLOAD_NO_FILE, "该文件不存在");
        }
        String processingStr = processingObj.toString();
        boolean processing = Boolean.parseBoolean(processingStr);
        String value = redisTemplate.opsForValue().get(Constants.FILE_MD5_KEY + md5);
        if (processing) { //上传成功
            return new Message().ok(RespCode.UPLOAD_FILE_EXIST, "该文件已存在");
        } else {
            File confFile = new File(value);
            byte[] completeList = FileUtils.readFileToByteArray(confFile);
            List<String> currentChunkList = new LinkedList<>();
            for (int i = 0; i < completeList.length; i++) {
                if (completeList[i] == Byte.MAX_VALUE) {
                    currentChunkList.add(i + "");
                }else {
                    break;
                }
            }
            int process = currentChunkList.size();
            return new Message().ok(RespCode.UPLOAD_HAVE_FILE, "该文件已存在一部分")
                    .addData("process", "" + process);
        }
    }


    @PostMapping("/fileUpload")
    public Message fileUpload(HttpServletRequest request) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            logger.info("上传文件start。");
            try {
                // 方法1
                //storageService.uploadFileRandomAccessFile(param);
                // 方法2 这个更快点
                storageService.uploadFileByMappedByteBuffer(request);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("文件上传失败}");
            }
            logger.info("上传文件end。");
        } else {
            return new Message().error(500, "请求出错");
        }
        return new Message().ok(200,"上传成功");
    }


}
