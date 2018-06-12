package com.newx.muv.utils.scaner;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.newx.base.utils.text.TextUtil;
import com.newx.base.utils.utilcode.util.FileUtils;
import com.newx.entity.def.INVALID;
import com.newx.muv.base.thread.ThreadUtil;
import com.newx.muv.utils.scaner.imp.OnScanCompletedListener;
import com.newx.muv.utils.scaner.imp.OnScanProcessListener;
import com.newx.muv.utils.scaner.imp.OnScanResultListener;
import com.newx.muv.utils.scaner.imp.OnSearchProcessListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by xuzhijian on 2018/5/23 0023.
 * 文件扫描
 */

public class MediaScanner {

    private MediaScannerConnection mConn = null;
    private ScannerClient mClient;

    private String mScanDirPath;
    private OnScanProcessListener mScanProcessListener;
    private OnScanResultListener mScanResultListener;
    private OnScanCompletedListener mScanCompletedListener;
    private OnSearchProcessListener mSearchProcessListener;

    private int mScanCompletedCount = 0; //扫描完成的数量
    private List<File> mFileList;
    private List<String> mExtensionFilter; //后缀过滤
    private int mMinSizeFilter; //文件大小过滤 - 最小
    private int mMaxSizeFilter; //文件大小过滤 - 最大

    private MediaScanner(Context context) {
        if (mClient == null) {
            mClient = new ScannerClient();
        }
        if (mConn == null) {
            mConn = new MediaScannerConnection(context, mClient);
        }
    }

    public void scan() {

        if (TextUtil.isEmpty(mScanDirPath)) {
            return;
        }

        mFileList = FileUtils.listFilesInDirWithFilter(mScanDirPath,
                file -> doFilter(file), true);

        if (mFileList.size() == 0) {
            mScanCompletedListener.scanCompleted(0);
            return;
        }
        mConn.connect();
    }

    private boolean doFilter(File file) {
        //回调搜索状态
        if (mSearchProcessListener != null && file.isDirectory()) {
            mSearchProcessListener.onSearching(file.getAbsolutePath());
        }

        //过滤 Size 和 Extension
        if (mMinSizeFilter == INVALID.INTEGER
                && mMaxSizeFilter == INVALID.INTEGER
                && mExtensionFilter.size() == 0) {
            return true;
        }

        boolean extensionFlag = mExtensionFilter.contains(FileUtils.getFileExtension(file));

        if (!extensionFlag) {
            return false;
        }

        long fileSize = file.length() / 1024;

        if (mMinSizeFilter != INVALID.INTEGER) {
            if (fileSize < mMinSizeFilter) {
                return false;
            }
        }

        if (mMaxSizeFilter != INVALID.INTEGER) {
            if (fileSize > mMaxSizeFilter) {
                return false;
            }
        }

        return true;
    }

    @NonNull
    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

    private class ScannerClient implements MediaScannerConnection.MediaScannerConnectionClient {

        @Override
        public void onMediaScannerConnected() {
            if (mFileList == null) {
                return;
            }

            scan(mFileList);
        }

        @Override
        public void onScanCompleted(String path, Uri uri) {
            if (mScanResultListener != null) {
                mScanResultListener.onScanResult(path);
            }
            mConn.disconnect();
            mScanCompletedCount = mScanCompletedCount + 1;

            if (mScanCompletedListener != null && mFileList != null
                    && mScanCompletedCount >= mFileList.size()) {
                //UI 切回主线程
                ThreadUtil.doInMainThread(o -> mScanCompletedListener.scanCompleted(mFileList.size()));
            }

        }

        /**
         * 扫描 List
         *
         * @param fileList
         */
        private void scan(List<File> fileList) {
            for (int i = 0; i < fileList.size(); i++) {
                scanOpt(fileList.get(i));
            }
        }

        /**
         * 扫描操作
         *
         * @param file
         */
        private void scanOpt(File file) {
            mConn.scanFile(file.getAbsolutePath(), null);

            if (mScanProcessListener != null) {
                mScanProcessListener.onScanning(file.getAbsolutePath());
            }
        }
    }


    /**
     * MediaScanner 的创建
     */
    public static class Builder {

        private Context context;
        private OnScanProcessListener scanProcessListener;
        private OnScanResultListener scanResultListener;
        private OnScanCompletedListener scanCompletedListener;
        private OnSearchProcessListener searchProcessListener;
        private List<String> extensionFilter; //后缀过滤
        private String scanDirPath; //扫描的文件夹
        private int mMinSizeFilter = INVALID.INTEGER;
        private int mMaxSizeFilter = INVALID.INTEGER;
//        private boolean ignoreCase = true; //过滤是否需要大小写

        public Builder(Context context) {
            this.context = context;
            this.extensionFilter = new ArrayList<>();
        }

        public Builder scanDirPath(String scanDirPath) {
            this.scanDirPath = scanDirPath;
            return this;
        }

        public Builder extension(String extension) {
            extensionFilter.add(extension.toLowerCase());
            return this;
        }

        public Builder extension(String... extension) {
            for (String ext : extension) {
                extensionFilter.add(ext.toLowerCase());
            }
            return this;
        }

        public Builder minSize(int minSize) {
            mMinSizeFilter = minSize;
            return this;
        }

        public Builder maxSize(int maxSize) {
            mMaxSizeFilter = maxSize;
            return this;
        }

        public Builder onSearchProcess(OnSearchProcessListener searchProcessListener) {
            this.searchProcessListener = searchProcessListener;
            return this;
        }

        public Builder onScanProcess(OnScanProcessListener scanProcessListener) {
            this.scanProcessListener = scanProcessListener;
            return this;
        }

        public Builder onScanResult(OnScanResultListener scanResultListener) {
            this.scanResultListener = scanResultListener;
            return this;
        }

        public Builder onScanCompleted(OnScanCompletedListener scanCompletedListener) {
            this.scanCompletedListener = scanCompletedListener;
            return this;
        }

        public MediaScanner build() {
            MediaScanner scanner = new MediaScanner(context);
            scanner.mScanDirPath = scanDirPath;
            scanner.mExtensionFilter = extensionFilter;
            scanner.mMinSizeFilter = mMinSizeFilter;
            scanner.mMaxSizeFilter = mMaxSizeFilter;
            scanner.mSearchProcessListener = searchProcessListener;
            scanner.mScanProcessListener = scanProcessListener;
            scanner.mScanResultListener = scanResultListener;
            scanner.mScanCompletedListener = scanCompletedListener;
            return scanner;
        }
    }

}
