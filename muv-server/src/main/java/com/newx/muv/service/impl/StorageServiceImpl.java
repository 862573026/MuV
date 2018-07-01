package com.newx.muv.service.impl;

import com.newx.muv.common.DefKey;
import com.newx.muv.test.FileSplitUtil;
import com.newx.muv.service.StorageService;
import com.newx.muv.util.FileMD5Util;
import com.newx.muv.util.RequestResponseUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by NewX on 2017/5/2.
 */
@Service
public class StorageServiceImpl implements StorageService {

    private final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);
    // 保存文件的根目录 - 项目配置
    private final Path rootPath;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final String tempDir = "/tmp/";

    @Autowired
    public StorageServiceImpl(@Value("${upload.path}") String location) {
        this.rootPath = Paths.get(System.getProperty("user.dir") + location);
    }

    @Override
    public void deleteAll() {
        logger.info("开发初始化清理数据，start");
        FileSystemUtils.deleteRecursively(rootPath.toFile());
        redisTemplate.delete(DefKey.FILE_UPLOAD_STATUS);
        redisTemplate.delete(DefKey.FILE_MD5_KEY);
        logger.info("开发初始化清理数据，end");
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootPath);
        } catch (FileAlreadyExistsException e) {
            logger.error("文件夹已经存在了，不用再创建。");
        } catch (IOException e) {
            logger.error("初始化root文件夹失败。", e);
        }
    }

    @Override
    public void uploadFileByMappedByteBuffer(ServletRequest request, String uploadDir) throws IOException {
        Map<String, String> paramMap = RequestResponseUtil.getRequestParameters(request);
        MultipartFile file = RequestResponseUtil.getRequestMultiParameters(request, "file");

        String fileName = paramMap.get("name");
        String md5 = paramMap.get("md5");

        StringBuffer sb = new StringBuffer();
        sb.append(rootPath);
        if (null != uploadDir) {
            sb.append("/").append(uploadDir);
        }
        sb.append("/").append(md5);
        String uploadDirPath = sb.toString();
        String uploadDirTmpPath = uploadDirPath + tempDir;
        String tempFileName = file.getOriginalFilename();
        File tmpDir = new File(uploadDirTmpPath);
        File tmpFile = new File(uploadDirTmpPath, tempFileName);
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }

        RandomAccessFile tempRaf = new RandomAccessFile(tmpFile, "rw");
        FileChannel fileChannel = tempRaf.getChannel();

        //写入该分片数据
        long offset = file.getSize();
        byte[] fileData = file.getBytes();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, offset, fileData.length);
        mappedByteBuffer.put(fileData);
        // 释放
        FileMD5Util.freedMappedByteBuffer(mappedByteBuffer);
        fileChannel.close();

        boolean isOk = checkAndSetUploadProgress(paramMap, uploadDirPath);
        if (isOk) {
            FileSplitUtil.merge(uploadDirTmpPath, uploadDirPath, fileName);
            boolean flag = renameFile(tmpFile, fileName);
            // 重命名后删除临时的分片文件
            if (flag) {
                com.newx.muv.util.FileUtils.deleteDir(uploadDirTmpPath);
            }
        }
    }

    /**
     * 检查并修改文件上传进度
     *
     * @param paramMap
     * @param uploadDirPath
     * @return
     * @throws IOException
     */
    private boolean checkAndSetUploadProgress(Map<String, String> paramMap, String uploadDirPath) throws IOException {
        String fileName = paramMap.get("name");
        String md5 = paramMap.get("md5");
        int chunks = Integer.parseInt(paramMap.get("chunks"));
        int chunk = Integer.parseInt(paramMap.get("chunk"));

        File confFile = new File(uploadDirPath, fileName + ".conf");
        RandomAccessFile accessConfFile = new RandomAccessFile(confFile, "rw");
        //把该分段标记为 true 表示完成
        accessConfFile.setLength(chunks);
        accessConfFile.seek(chunk);
        accessConfFile.write(Byte.MAX_VALUE);

        //completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
        byte[] completeList = FileUtils.readFileToByteArray(confFile);
        byte isComplete = Byte.MAX_VALUE;
        for (int i = 0; i < completeList.length && isComplete == Byte.MAX_VALUE; i++) {
            //与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
            isComplete = (byte) (isComplete & completeList[i]);
        }

        accessConfFile.close();
        if (isComplete == Byte.MAX_VALUE) {
            redisTemplate.opsForHash().put(DefKey.FILE_UPLOAD_STATUS, md5, "true");
            redisTemplate.opsForValue().set(DefKey.FILE_MD5_KEY + md5, uploadDirPath + "/" + fileName);
            return true;
        } else {
            if (!redisTemplate.opsForHash().hasKey(DefKey.FILE_UPLOAD_STATUS, md5)) {
                redisTemplate.opsForHash().put(DefKey.FILE_UPLOAD_STATUS, md5, "false");
            }
            if (!redisTemplate.hasKey(DefKey.FILE_MD5_KEY + md5)) {
                redisTemplate.opsForValue().set(DefKey.FILE_MD5_KEY + md5, uploadDirPath + "/" + fileName + ".conf");
            }
            return false;
        }
    }

    /**
     * 文件重命名
     *
     * @param toBeRenamed   将要修改名字的文件
     * @param toFileNewName 新的名字
     * @return
     */
    public boolean renameFile(File toBeRenamed, String toFileNewName) {
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
            logger.info("File does not exist: " + toBeRenamed.getName());
            return false;
        }
        String p = toBeRenamed.getParent();
        File newFile = new File(p + File.separatorChar + toFileNewName);
        //修改文件名
        return toBeRenamed.renameTo(newFile);
    }
}
