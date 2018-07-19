package com.newx.splash.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.newx.base.route.RP;
import com.newx.common.proxy.ResourceProxy;
import com.newx.common.helper.JumpHelper;
import com.newx.net.CommunicationService;
import com.newx.utils.thread.ThreadUtil;


/**
 * Created by xuzhijian on 2018/4/20 0020.
 * 初始化的Activity -> 做一下处理操作
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThreadUtil.doInIOThread(o -> {
            Intent intent = new Intent(ResourceProxy.getApp(), CommunicationService.class);
            //8.0不允许startService启动Service -> 且有前台限制，5s不调用startService就ANR了
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        startForegroundService(intent);
//                    } else {
//                        startService(intent);
//                    }
            startService(intent);
        });

        JumpHelper.containerFragment(RP.SplashFragment)
                .navigation();

        finish();

//        Intent intent = new Intent(InitActivity.this, CommunicationService.class);
//        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service != null) {

            }
        }
    };
}
