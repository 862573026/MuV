package com.newx.muv.task;

import com.newx.base.utils.text.TextUtil;
import com.newx.base.utils.utilcode.util.FileUtils;
import com.newx.muv.base.thread.ThreadUtil;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 文件分割
 */
public class FileSplitTask {

    public static final long KB = 1024;
    public static final long MB = 1024 * KB;


    private String mFilePath;  //分片文件路径
    private long mChunkSize; //分片大小
    private OnProgressListener mProgressListener;
    private OnCompleteListener mCompleteListener;

    private FileSplitTask() {
    }


    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * 执行 - 异步
     */
    public void execute() {
        ThreadUtil.doInIOThread(o -> split());
    }

    /**
     * 分割操作
     *
     * @throws IOException
     */
    private void split() throws IOException {
        if (TextUtil.isEmpty(mFilePath)) {
            return;
        }

        File sourceFile = new File(mFilePath);
        byte[] b = new byte[1024];        //这个不解释 如果看不懂 就去看IO流去吧
        RandomAccessFile raf1 = new RandomAccessFile(sourceFile, "r");
        int len = -1;
        int numberOfPieces = (int) Math.ceil((double) sourceFile.length() / (double) mChunkSize);
        if (numberOfPieces == 0) {
            return;
        }
        String splitDir = FileSplitDef.SPLIT_DIR + FileUtils.getFileMD5ToString(sourceFile);
        String splitName = splitDir + "/" + sourceFile.getName() + FileSplitDef.TEMP_FILE_NAME;

        FileUtils.createOrExistsDir(splitDir);
        for (int i = 0; i < numberOfPieces; i++) {
            if (mProgressListener != null) {
                mProgressListener.onSplit(i + 1);
            }
            String name = splitName + (i + 1);
            File file = new File(name);
            FileUtils.createFileByDeleteOldFile(file);
            RandomAccessFile raf2 = new RandomAccessFile(file, "rw");
            while ((len = raf1.read(b)) != -1) {
                raf2.write(b, 0, len);
                if (raf2.length() >= mChunkSize)
                    break;
            }
            raf2.close();
        }
        raf1.close();
        if (mCompleteListener != null) {
            mCompleteListener.onComplete(splitDir);
        }
    }

    public static class Builder {

        private String filePath;  //分片文件路径
        private long chunkSize; //分片大小
        private OnProgressListener progressListener;
        private OnCompleteListener completeListener;

        public Builder chunkSize(long chunkSize) {
            this.chunkSize = chunkSize;
            return this;
        }

        public Builder chunkSizeKB(long chunkSize) {
            this.chunkSize = chunkSize * KB;
            return this;
        }

        public Builder chunkSizeMB(long chunkSize) {
            this.chunkSize = chunkSize * MB;
            return this;
        }

        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder onProgress(OnProgressListener progressListener) {
            this.progressListener = progressListener;
            return this;
        }

        public Builder onComplete(OnCompleteListener completeListener) {
            this.completeListener = completeListener;
            return this;
        }

        public FileSplitTask build() {
            FileSplitTask splitTask = new FileSplitTask();
            splitTask.mFilePath = filePath;
            splitTask.mChunkSize = chunkSize;
            splitTask.mProgressListener = progressListener;
            splitTask.mCompleteListener = completeListener;
            return splitTask;
        }

        public void execute() {
            build().execute();
        }
    }
}
