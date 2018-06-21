package com.newx.muv.vm;

import android.content.Context;
import android.graphics.Bitmap;

import com.newx.base.frameworks.util.log.NXLog;
import com.newx.base.utils.text.GsonUtil;
import com.newx.base.utils.utilcode.subutil.PathUtils;
import com.newx.entity.base.Message;
import com.newx.base.utils.utilcode.util.FileUtils;
import com.newx.entity.local.VideoInfo;
import com.newx.muv.base.databinding.command.BindingCommand;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.http.Http;
import com.newx.muv.task.FileSplitTask;
import com.newx.muv.utils.VideoUtil;
import com.newx.muv.utils.dialog.DialogBuilderHelper;

import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by xuzhijian on 2018/5/28 0028.
 * 本地视频 Item
 */

public class ItemLocalVideoVM extends NSBaseVM {

    public Context mContext;
    public VideoInfo mEntity;
    public Bitmap mThumb; //内存

    public ItemLocalVideoVM(Context context, VideoInfo entity) {
        mContext = context;
        mEntity = entity;
        mThumb = VideoUtil.getVideoThumbnail(mEntity.getPath());
    }

    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(() -> {

//        String filePath = PathUtils.getExtDownloadsPath() + "/Android.pdf";
//        FileSplitTask.newBuilder()
//                .filePath(PathUtils.getExtDownloadsPath() + "/Android.pdf")
//                .chunkSizeMB(1)
//                .onProgress(position -> NXLog.e("正在分片，当前是第" + position + "片"))
//                .onComplete(path -> {
//                    checkFileMD5(filePath, path);
//                    NXLog.e("全部分片完成，存储路径是：" + path);
//                })
//                .execute();

//        FileMergeTask.newBuilder()
//                .filePath(PathUtils.getExtDownloadsPath() + "/HK_Buy22114.PNG")
//                .onProgress(position -> NXLog.e("正在合并，当前是第" + position + "片"))
//                .onComplete(path -> NXLog.e("全部合并完成，存储路径是：" + path))
//                .execute();

    });

    public void checkFileMD5(String filePath, String fileDir) {
        String fileMd5 = FileUtils.getFileMD5ToString(filePath);

        Http.getApi().checkFileMd5(fileMd5)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                            NXLog.e(response.toString());
                            Message message = GsonUtil.toEntity(response, Message.class);
                            int code = message.getCode();
                            if (code == 2000) { //文件不存在，开始上传
                                uploadFile(filePath, fileDir, 0);
                            } else if (code == 2001) {//文件已存在，不用上传

                            } else if (code == 2002) { //文件上传了一部分
                                int process = Integer.parseInt((String) message.getData().get("process"));
                                uploadFile(filePath, fileDir, process);
                            }
                        },
                        throwable -> DialogBuilderHelper.showTipDialog(mContext, throwable.getMessage()));
    }


    /**
     * 上传文件
     *
     * @param filePath 文件地址
     * @param dirPath  分块地址
     * @param chunk    当前分块
     */
    public void uploadFile(String filePath, String dirPath, int chunk) {

        File file = FileUtils.getFileByPath(filePath);
        long fileSize = file.length();
        String md5 = FileUtils.getFileMD5ToString(file);
        List<File> files = FileUtils.listFilesInDir(dirPath);
        int chunks = files.size();

        Http.getApi().fileUpload("testUid", "taskId", file.getName(),
                chunks, chunk, fileSize, md5,
                MultipartBody.Part.createFormData("file",file.getName(), RequestBody.create(MultipartBody.FORM,files.get(chunk))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> checkFileMD5(filePath, dirPath),
                        throwable -> DialogBuilderHelper.showTipDialog(mContext, throwable.getMessage()));

    }


}
