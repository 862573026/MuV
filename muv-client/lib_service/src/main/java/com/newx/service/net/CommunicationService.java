package com.newx.service.net;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by xuzhijian on 2018/4/20 0020.
 */

public class CommunicationService extends Service {

    private static CommunicationService  mCommunicationService;


    private ServerManager mServerManager;

    @Override
    public void onCreate() {
        super.onCreate();

        mCommunicationService = this;

        mServerManager = new ServerManager();
        mServerManager.loadServer();
    }

    /**
     * 获取 ServerManager
     * @return
     */
    public static ServerManager getServerManager() {
        return mCommunicationService.mServerManager;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new CommunicationServiceBinder();
    }


    public class CommunicationServiceBinder extends Binder {

        public CommunicationService getService() {
            return CommunicationService.this;
        }
    }

    /**
     * Service弹出Dialog -> 需要转为 TYPE_SYSTEM_ALERT 系统的Dialog
     * 因为Dialog需要依赖，没界面弹什么Dialog，也不建议在Service弹Dialog
     * @param title
     * @param content
     * @param positiveText
     */
    public static void showDialog(String title,String content,String positiveText){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(mCommunicationService)
                .title(title)
                .content(content)
                .positiveText(positiveText);

        MaterialDialog dialog = builder.build();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }
}
