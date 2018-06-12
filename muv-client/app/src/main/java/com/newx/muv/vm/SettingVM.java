package com.newx.muv.vm;

import android.content.Context;

import com.newx.base.frameworks.util.log.NXLog;
import com.newx.base.frameworks.widget.toast.ToastUtil;
import com.newx.base.proxy.ResourceProxy;
import com.newx.base.ui.fragmentation.SupportFragmentDelegate;
import com.newx.base.utils.mobile.SPUtil;
import com.newx.base.utils.utilcode.constant.PermissionConstants;
import com.newx.base.utils.utilcode.subutil.PathUtils;
import com.newx.base.utils.utilcode.util.PermissionUtils;
import com.newx.entity.def.DEFAULT;
import com.newx.entity.local.ServersEntity;
import com.newx.entity.local.VideoInfo;
import com.newx.muv.R;
import com.newx.muv.base.def.SPConfig;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.mapper.VideoInfoMapper;
import com.newx.muv.utils.VideoUtil;
import com.newx.muv.utils.dialog.DialogBuilderHelper;
import com.newx.muv.utils.scaner.MediaScanner;
import com.newx.service.net.CommunicationService;
import com.newx.service.utils.HttpUtil;

import java.util.List;

/**
 * Created by xuzhijian on 2018/5/11 0011.
 */

public class SettingVM extends NSBaseVM {

    private Context mContext;


    public SettingVM(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    List<ServersEntity.ServerBean> listServer = CommunicationService.getServerManager().getServers();

    public void switchServer() {
        int selectIndex = (int) SPUtil.get(SPConfig.KEY.KEY_SELECT_SERVER_INDEX, DEFAULT.INDEX);

        DialogBuilderHelper.showSingleChoiceDialog(mContext, selectIndex, ResourceProxy.getString(R.string.choose_server_text), listServer
                , (dialog, itemView, which, text) -> {
                    ToastUtil.showShort(ResourceProxy.getString(R.string.chosen_result_text, text));
                    ServersEntity.ServerBean select = CommunicationService.getServerManager().getServers().get(which);
                    String httpUrl = select.getIp() + ":" + select.getHttp_port();
                    SPUtil.put(SPConfig.KEY.KEY_SELECT_SERVER_INDEX, which);
                    SPUtil.put(SPConfig.KEY.KEY_SELECT_SERVER_HTTP, httpUrl);
                    HttpUtil.setBaseURL(httpUrl);
                    return false;
                });
    }

    public void scanLocalVideo() {

        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        MediaScanner scanner = MediaScanner.newBuilder(mContext)
                                .scanDirPath(PathUtils.getExtStoragePath())
                                .extension("mp4")
//                                    .minSize(1000)
//                                    .maxSize(2000)
                                .onSearchProcess(path -> NXLog.i("正在查找 " + path))
                                .onScanProcess(path -> NXLog.i("正在扫描 " + path))
                                .onScanResult(path -> NXLog.i("扫描完成 " + path))
                                .onScanCompleted(count -> {
                                    NXLog.w(count + "个文件,全部扫描完成");
                                    List<VideoInfo> videoInfoList = VideoUtil.getVideoInfoList(mContext);
                                })
                                .build();

                        scanner.scan();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {

                    }
                })
                .request();
    }
}
