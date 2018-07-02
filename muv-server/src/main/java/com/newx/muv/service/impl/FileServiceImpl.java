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
    public String upload(String name,
                       String relativePath,
                       String md5,
                       MultipartFile file) throws IOException {
        String[] path = getUploadPath(relativePath, md5, name);
        java.io.File dir = new java.io.File(path[0]);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileIOUtils.writeFileFromIS(path[1], file.getInputStream());
        mFileMapper.save(new File(name, md5, path[1], new Date()));
        return path[1];
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
    public String uploadWithBlock(String name,
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

        FileIOUtils.writeWithBlok(path[1], size, file.getInputStream(), file.getSize(), chunks, chunk); //写入分片
        mRedisTemplate.opsForHash().put(DefKey.FILE_UPLOAD_CHUNK + md5, md5, chunk+"");
        int current = getCurrentChunk(md5);
        System.out.println("current:" + current + " => md5:" + md5);

        if(current == (chunks - 1)){ // 因为从0开始
            mRedisTemplate.delete(DefKey.FILE_UPLOAD_CHUNK + md5);
            mFileMapper.save(new File(name, md5, path[1], new Date()));
        }
        return path[1];
    }

    /**
     * 检查Md5判断文件是否已上传
     *
     * @param md5
     * @return -2001：不存在
     *          -2002：已存在
     *          chunk： 当前分片
     */
    @Override
    public int checkMd5(String md5) {
        int status = getStatusByMd5(md5);
        return status;
    }

    /**
     * 判断文件是否存在
     * @param md5
     * @return
     */
    private boolean isExist(String md5) {
        File file = new File();
        file.setMd5(md5);
        File result = mFileMapper.getByFile(file);
        if (result == null) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 获取当前上传的分片
     * @param md5
     * @return
     */
    private int getCurrentChunk(String md5){
        Object chunk = mRedisTemplate.opsForHash().get(DefKey.FILE_UPLOAD_CHUNK + md5, md5);
        if(chunk == null){
            return RespCode.UPLOAD_NO_FILE;
        }else {
            if(chunk instanceof String){
                String chunkStr = (String) chunk;
                return Integer.parseInt(chunkStr);
            }
            return RespCode.UPLOAD_FILE_ERROR;
        }
    }

    /**
     * 根据md5 获取文件上传状态
     * UPLOAD_FILE_NO 文件不存在
     * UPLOAD_FILE_EXIST 文件已存在
     * chunk 当前进度
     *
     * @param md5
     * @return
     */
    private int getStatusByMd5(String md5) {
        if (isExist(md5)) {
            return RespCode.UPLOAD_FILE_EXIST;
        } else {
            return getCurrentChunk(md5);
        }
//        return RespCode.UPLOAD_FILE_ERROR;
    }



    private String[] getUploadPath(String relativePath, String md5, String fileName) {
        String[] path = new String[2];
        StringBuffer sb = new StringBuffer();
        sb.append(UploadConfig.path)
                .append(relativePath);
        path[0] = sb.toString();
        sb.append(md5);
        path[1] = sb.toString();
        return path;
    }

}
