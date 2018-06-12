package com.newx.muv.vm;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.os.Handler;
import android.os.Looper;

import com.newx.base.frameworks.util.log.NXLog;
import com.newx.base.proxy.ResourceProxy;
import com.newx.base.ui.fragmentation.SupportFragmentDelegate;
import com.newx.base.utils.utilcode.constant.PermissionConstants;
import com.newx.base.utils.utilcode.subutil.PathUtils;
import com.newx.base.utils.utilcode.util.PermissionUtils;
import com.newx.entity.local.VideoInfo;
import com.newx.muv.BR;
import com.newx.muv.R;
import com.newx.muv.base.databinding.command.BindingAction;
import com.newx.muv.base.databinding.command.BindingCommand;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.base.thread.ThreadUtil;
import com.newx.muv.mapper.VideoInfoMapper;
import com.newx.muv.utils.VideoUtil;
import com.newx.muv.utils.scaner.MediaScanner;

import java.util.List;

import io.reactivex.functions.Consumer;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by xuzhijian on 2018/5/21 0021.
 * 上传的 VM
 */

public class UploadVM extends NSBaseVM {

    private Context mContext;

    private VideoInfoMapper mVideoInfoMapper;

    public ItemBinding<ItemLocalVideoVM> mItemBinding = ItemBinding.of(BR.itemLocalVideoVM, R.layout.item_local_video);

    public ObservableList<ItemLocalVideoVM> mLocalVideoList = new ObservableArrayList<>();

    public ObservableBoolean mFinishLoadMore = new ObservableBoolean(false);


    public int offset = 0; //当前条目
    public final int limit = 10; //每条数目


    public UploadVM(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mVideoInfoMapper = new VideoInfoMapper();
        onLoadMoreCommand.execute();
    }

    public void scanFile() {
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        scanTask();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {

                    }
                })
                .request();
    }

    private void scanTask() {
        MediaScanner scanner = MediaScanner.newBuilder(mContext)
                .scanDirPath(PathUtils.getExtStoragePath())
                .extension("mp4")
                .onSearchProcess(path -> NXLog.v("正在查找 " + path))
                .onScanProcess(path -> NXLog.d("正在扫描 " + path))
                .onScanResult(path -> NXLog.i("扫描完成 " + path))
                .onScanCompleted(count -> {
                    NXLog.w(count + "个文件,全部扫描完成");
                    List<VideoInfo> list = VideoUtil.getVideoInfoList(mContext);

                    ThreadUtil.doInIOThread(o -> {
                        mVideoInfoMapper.insertAll(list);
                    });

                    mLocalVideoList.clear();

                    if (list.size() > limit) {
                        convertList(list.subList(0, limit));
                    } else {
                        convertList(list);
                    }


                })
                .build();

        scanner.scan();
    }

    public BindingCommand onLoadMoreCommand = new BindingCommand(() -> loadMoreVideoInfo());


    public void loadMoreVideoInfo() {
        List<VideoInfo> resultList = mVideoInfoMapper.queryAll(offset, limit);
        if (resultList.size() == limit) { //不是最后一条

        } else if (resultList.size() < limit) {

        }

        convertList(resultList);
    }

    /**
     * 转换列表
     *
     * @param list
     */
    private void convertList(List<VideoInfo> list) {
        ThreadUtil.doInMainThread(o -> {
            for (int i = 0; i < list.size(); i++) {
                mLocalVideoList.add(new ItemLocalVideoVM(mContext, list.get(i)));
            }
            offset = mLocalVideoList.size();
            mFinishLoadMore.set(!mFinishLoadMore.get());
        });

    }

}
