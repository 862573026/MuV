package com.newx.muv.test;

import com.newx.muv.util.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * 文件分割
 */
public class FileSplitUtil {

    public static final long KB = 1024;
    public static final long MB = 1024 * KB;
    public static final String TEMP_DIR = "/temp/";

    public static final String SPLIT_DIR = System.getProperty("user.dir") + "/temp/split/";


    /**
     * 分割文件 - 根据数量分割
     *
     * @param filePath       文件路径
     * @param numberOfPieces 分割数目
     * @throws IOException
     */
    public static void splitByPieces(String filePath, int numberOfPieces) throws IOException {
        File sourceFile = new File(filePath);
        long fileLength = sourceFile.length() / numberOfPieces;        //分一下每一个小文件的大小
        byte[] b = new byte[1024];        //这个不解释 如果看不懂 就去看IO流去吧
        RandomAccessFile raf1 = new RandomAccessFile(sourceFile, "r");
        int len = -1;

        String splitDir = SPLIT_DIR + sourceFile.getParent();
        String splitName = SPLIT_DIR + filePath + ".";


        FileUtils.createOrExistsDir(splitDir);
        for (int i = 0; i < numberOfPieces; i++) {
            String name = splitName + (i + 1);
            File file = new File(name);
            file.createNewFile();
            RandomAccessFile raf2 = new RandomAccessFile(file, "rw");
            while ((len = raf1.read(b)) != -1) {
                raf2.write(b, 0, len);        //我觉的这样写比raf2.write(b);高明一些
                if (raf2.length() > fileLength)        //如果太大了就不在这个子文件写了 换下一个
                    break;
            }
            raf2.close();
        }
        raf1.close();
    }

    public static void splitByChunkSizeMB(String filePath, long chunkSize) throws IOException {
        splitByChunkSize(filePath, chunkSize * KB);
    }

    public static void splitByChunkSizeKB(String filePath, long chunkSize) throws IOException {
        splitByChunkSize(filePath, chunkSize * MB);
    }

    /**
     * 分割文件 - 根据大小分割
     *
     * @param filePath
     * @param chunkSize
     * @throws IOException
     */
    public static void splitByChunkSize(String filePath, long chunkSize) throws IOException {
        File sourceFile = new File(filePath);
        byte[] b = new byte[1024];        //这个不解释 如果看不懂 就去看IO流去吧
        RandomAccessFile raf1 = new RandomAccessFile(sourceFile, "r");
        int len = -1;
        int numberOfPieces = (int) (sourceFile.length() / chunkSize);
        String splitDir = SPLIT_DIR + sourceFile.getParent();
        String splitName = SPLIT_DIR + filePath + ".";


        FileUtils.createOrExistsDir(splitDir);
        for (int i = 0; i < numberOfPieces; i++) {
            String name = splitName + (i + 1);
            File file = new File(name);
            file.createNewFile();
            RandomAccessFile raf2 = new RandomAccessFile(file, "rw");
            while ((len = raf1.read(b)) != -1) {
                raf2.write(b, 0, len);
                if (raf2.length() >= chunkSize)
                    break;
            }
            raf2.close();
        }
        raf1.close();
    }


    /**
     * 合并
     *
     * @param dirPath
     * @throws IOException
     */
    public static void merge(String dirPath,String targetPath, String fileName) throws IOException {
        if (!FileUtils.isFileExists(dirPath) || !FileUtils.isDir(dirPath)) {
            return;
        }

        List<File> files = FileUtils.listFilesInDirWithFilter(dirPath, pathname -> {
            if (pathname.getName().startsWith(fileName)) {
                return true;
            }
            return false;
        });

        File file = new File(targetPath + "/" + fileName);
        file.createNewFile();
        RandomAccessFile in = new RandomAccessFile(file, "rw");
        in.setLength(0);
        in.seek(0);
        byte[] bytes = new byte[1024];
        int len = -1;
        for (int i = 0; i < files.size(); i++) {
            RandomAccessFile out = new RandomAccessFile(files.get(i), "rw");
            while ((len = out.read(bytes)) != -1) {
                in.write(bytes, 0, len);
            }
            out.close();
        }
        in.close();
    }
}
