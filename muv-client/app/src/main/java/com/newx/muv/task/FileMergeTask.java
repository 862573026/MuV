package com.newx.muv.task;

import com.newx.base.utils.text.TextUtil;
import com.newx.base.utils.utilcode.util.FileUtils;
import com.newx.muv.base.thread.ThreadUtil;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by xuzhijian on 2018/5/31 0031.
 * 合并任务
 */

public class FileMergeTask {

    private FileMergeTask() {
    }

    private String mFilePath; //文件路径
    private boolean mDeleteTempAfterMerge = true;
    private OnProgressListener mProgressListener;
    private OnCompleteListener mCompleteListener;

    public static Builder newBuilder() {
        return new Builder();
    }


    /**
     * 异步执行
     */
    public void execute() {
        ThreadUtil.doInIOThread(o -> merge());
    }

    /**
     * 合并
     * * @throws IOException
     */
    private void merge() throws IOException {
        if (TextUtil.isEmpty(mFilePath)) {
            return;
        }
        File file = new File(mFilePath);
        String splitDir = FileSplitDef.SPLIT_DIR + FileUtils.getFileMD5ToString(file);
        if (!FileUtils.isFileExists(splitDir)) {
            return;
        }
        String tempFileName = splitDir + FileSplitDef.TEMP_FILE_NAME;
        int numberOfPieces = FileUtils.listFilesInDir(splitDir).size();
        if (numberOfPieces == 0) {
            return;
        }
        File[] files = new File[numberOfPieces];
        file.createNewFile();
        RandomAccessFile in = new RandomAccessFile(file, "rw");
        in.setLength(0);
        in.seek(0);
        byte[] bytes = new byte[1024];
        int len = -1;
        for (int i = 0; i < files.length; i++) {
            if (mProgressListener != null) {
                mProgressListener.onSplit(i + 1);
            }
            files[i] = new File(tempFileName + (i + 1));
            RandomAccessFile out = new RandomAccessFile(files[i], "rw");
            while ((len = out.read(bytes)) != -1) {
                in.write(bytes, 0, len);
            }
            out.close();
        }
        in.close();
        if (mDeleteTempAfterMerge) {
            FileUtils.deleteDir(splitDir);
        }
        if (mCompleteListener != null) {
            mCompleteListener.onComplete(mFilePath);
        }
    }


    public static class Builder {

        private String filePath;
        private OnProgressListener progressListener;
        private OnCompleteListener completeListener;

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

        public FileMergeTask build() {
            FileMergeTask mergeTask = new FileMergeTask();
            mergeTask.mFilePath = filePath;
            mergeTask.mProgressListener = progressListener;
            mergeTask.mCompleteListener = completeListener;
            return mergeTask;
        }

        public void execute() {
            build().execute();
        }
    }
}
