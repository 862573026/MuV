package com.newx.muv.service.impl;

import com.newx.muv.common.*;
import com.newx.muv.config.UploadConfig;
import com.newx.muv.dao.FileMapper;
import com.newx.muv.entity.bo.File;
import com.newx.muv.entity.vo.Message;
import com.newx.muv.service.FileService;
import com.newx.muv.util.FileIOUtils;
import com.newx.muv.util.FileUtils;
import com.newx.muv.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service("FileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private StringRedisTemplate mRedisTemplate;

    @Autowired
    private FileMapper mFileMapper;

    /**
     * 上传文件
     *
     * @param md5
     * @param file
     */
    @Override
    public void upload(String name,
                       String relativePath,
                       String md5,
                       MultipartFile file) throws IOException {
        String path = UploadConfig.path + FileUtils.generateFileName();
        FileIOUtils.writeFileFromIS(path, file.getInputStream());
        mFileMapper.save(new File(name, md5, path, new Date()));
    }

    /**
     * 分块上传文件
     *
     * @param md5
     * @param size
     * @param chunks
     * @param chunk
     * @param file
     * @throws IOException
     */
    @Override
    public void uploadWithBlock(String name,
                                String relativePath,
                                String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                MultipartFile file) throws IOException {
        String[] path = getUploadPath(relativePath, md5, name);
        java.io.File dir = new java.io.File(path[0]);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        FileIOUtils.writeWithBlok(path[1], size, file.getInputStream(), file.getSize(), chunks, chunk);
        mRedisTemplate.opsForHash().put(DefKey.FILE_UPLOAD_PROGRESS, md5, chunk + "");
        System.out.println("current:" + mRedisTemplate.opsForHash().get(DefKey.FILE_UPLOAD_PROGRESS, md5));
        if (UploadUtils.isUploaded(md5)) {
            UploadUtils.removeKey(md5);
            mFileMapper.save(new File(name, md5, path[1], new Date()));
        }
    }

    /**
     * 检查Md5判断文件是否已上传
     *
     * @param md5
     * @return
     */
    @Override
    public Message checkMd5(String md5) {
        File file = new File();
        file.setMd5(md5);
        Object status = mRedisTemplate.opsForHash().get(DefKey.FILE_UPLOAD_STATUS, md5);
        if (status == null) {
            return new Message().ok().addData(RespKey.STATUS, RespCode.UPLOAD_NO_FILE);
        } else {
            if (DefValue.FILE_EXIST.equals(status)) {
                return new Message().ok().addData(RespKey.STATUS, RespCode.UPLOAD_FILE_EXIST);
            } else if (DefValue.FILE_HAVE.equals(status)) {
                Object process = mRedisTemplate.opsForHash().get(DefKey.FILE_UPLOAD_PROGRESS, md5);
                return new Message().ok()
                        .addData(RespKey.STATUS, RespCode.UPLOAD_HAVE_FILE)
                        .addData(RespKey.PROGRESS, process);
            }
        }
        return new Message().error();
    }

    private String[] getUploadPath(String relativePath, String md5, String fileName) {
        String[] path = new String[2];
        StringBuffer sb = new StringBuffer();
        sb.append(UploadConfig.path)
                .append("/")
                .append(relativePath)
                .append("/")
                .append(md5);
        path[0] = sb.toString();
        sb.append("/").append(fileName);
        path[1] = sb.toString();
        return path;
    }

}
