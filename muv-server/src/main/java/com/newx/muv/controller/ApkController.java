package com.newx.muv.controller;

import com.github.pagehelper.PageInfo;
import com.newx.muv.common.RespCode;
import com.newx.muv.common.RespKey;
import com.newx.muv.common.RespMsg;
import com.newx.muv.controller.upload.Uploader;
import com.newx.muv.entity.bo.Apk;
import com.newx.muv.entity.vo.Message;
import com.newx.muv.service.ApkService;
import com.newx.muv.util.RequestResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by xuzhijian on 2018/6/26 0026.
 * Apk管理
 */
@RestController
@RequestMapping("/manager/apk")
public class ApkController extends BasicAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApkController.class);

    @Autowired
    private ApkService mApkService;

    @Autowired
    Uploader mUploader;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取Apk列表", httpMethod = "GET")
    @GetMapping("/list")
    public Message getApkList(HttpServletRequest request) {
        RequestResponseUtil.pageHelper(request);
        List<Apk> roles = mApkService.getApkList();
        PageInfo pageInfo = new PageInfo(roles);
        return new Message().ok(RespCode.OK, RespMsg.SUCCESS)
                .addData(RespKey.PAGE_INFO, pageInfo);
    }

    @ApiOperation(value = "添加Apk", httpMethod = "POST")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Message addApk(@RequestBody Apk apk) {
        LOGGER.info(apk.toString());

        boolean flag = mApkService.insert(apk);
        if (flag) {
            return new Message().ok(RespCode.OK, RespMsg.SUCCESS);
        } else {
            return new Message().error(RespCode.ERROR, RespMsg.ERROR);
        }
    }
    @ApiOperation(value = "获取文件的上传状态", httpMethod = "GET")
    @GetMapping("/checkMd5")
    public int checkMd5(String md5) {
        return mUploader.checkMd5(md5);
    }


    @ApiOperation(value = "上传Apk", httpMethod = "POST")
    @PostMapping("/upload")
    public String chunkUpload(ServletRequest request) throws IOException {
        return mUploader.upload(request);
    }

    @ApiOperation(value = "更新Apk", httpMethod = "PUT")
    @PutMapping("/update")
    public Message updateApk(@RequestBody Apk apk) {
        LOGGER.info(apk.toString());
        boolean flag = mApkService.update(apk);
        if (flag) {
            return new Message().ok(RespCode.OK, RespMsg.SUCCESS);
        } else {
            return new Message().error(RespCode.ERROR, RespMsg.ERROR);
        }
    }

    @ApiOperation(value = "根据Apk的ID删除Apk", httpMethod = "DELETE")
    @DeleteMapping("/delete/{id}")
    public Message deleteApkById(@PathVariable Integer id) {
        LOGGER.info(id.toString() + "==========");
        boolean flag = mApkService.deleteById(id);
        if (flag) {
            return new Message().ok(RespCode.OK, RespMsg.SUCCESS);
        } else {
            return new Message().error(RespCode.ERROR, RespMsg.ERROR);
        }
    }

}
