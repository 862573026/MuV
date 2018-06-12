package com.newx.muv.host;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.newx.base.frameworks.support.mvvm.NxBindingActivity;
import com.newx.base.ui.loading.WaveLoadingView;
import com.newx.base.utils.utilcode.constant.PermissionConstants;
import com.newx.base.utils.utilcode.util.PermissionUtils;
import com.newx.muv.host.databinding.ActivityHostBinding;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.qihoo360.replugin.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by newx on 18-6-3.
 * 宿主Activity
 */

public class HostActivity extends NxBindingActivity<ActivityHostBinding> {

    WaveLoadingView viewWavingLoading;


    @Override
    public int initContentView() {
        return R.layout.activity_host;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        viewWavingLoading = findViewById(R.id.view_wavingLoading);
//
//        viewWavingLoading.setProgressValue(20);
//        viewWavingLoading.setCenterTitle(20+"%");

//        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE)
//                .callback(new PermissionUtils.SimpleCallback() {
//                    @Override
//                    public void onGranted() {
//                    }
//
//                    @Override
//                    public void onDenied() {
//
//                    }
//                })
//                .request();

        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        installPlugin();
                    }

                    @Override
                    public void onDenied() {

                    }
                })
                .request();
    }


    /**
     * 检测插件状态
     */
    public void checkPluginList(){

    }

    /**
     * 下载插件
     */
    public void downloadPlugin(){

    }

    private void installPlugin(){
        final ProgressDialog pd = ProgressDialog.show(this, "Installing...", "Please wait...", true, true);
        // FIXME: 仅用于安装流程演示 2017/7/24
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                simulateInstallExternalPlugin("app.apk",
                        "com.newx.muv",
                        "com.newx.muv.view.init.InitActivity");

                installExternalPlugin("lib_camera.apk");

//                simulateInstallExternalPlugin("lib_camera.apk",
//                        "com.newx.camera",
//                        "com.newx.camera.activity.ActivityMain");

                pd.dismiss();
            }
        }, 1000);
    }


    /**
     * 模拟安装或升级（覆盖安装）外置插件
     * 注意：为方便演示，外置插件临时放置到Host的assets/external目录下，具体说明见README</p>
     */
    private void simulateInstallExternalPlugin(String apkName, String pluginName, String cls) {
        String apk = apkName;
        String apkPath = "external" + File.separator + apk;

        // 文件是否已经存在？直接删除重来
        String pluginFilePath = getFilesDir().getAbsolutePath() + File.separator + apk;
        File pluginFile = new File(pluginFilePath);
        if (pluginFile.exists()) {
            FileUtils.deleteQuietly(pluginFile);
        }

        // 开始复制
        copyAssetsFileToAppFiles(apkPath, apk);
        PluginInfo info = null;
        if (pluginFile.exists()) {
            info = RePlugin.install(pluginFilePath);
        }

        if (info != null) {
            RePlugin.startActivity(this,
                    RePlugin.createIntent(pluginName, cls));
        } else {
            Toast.makeText(this, "install external plugin failed", Toast.LENGTH_SHORT).show();
        }
    }


    private void installExternalPlugin(String apkName) {
        String apk = apkName;
        String apkPath = "external" + File.separator + apk;

        // 文件是否已经存在？直接删除重来
        String pluginFilePath = getFilesDir().getAbsolutePath() + File.separator + apk;
        File pluginFile = new File(pluginFilePath);
        if (pluginFile.exists()) {
            FileUtils.deleteQuietly(pluginFile);
        }

        // 开始复制
        copyAssetsFileToAppFiles(apkPath, apk);
        PluginInfo info = null;
        if (pluginFile.exists()) {
            info = RePlugin.install(pluginFilePath);
        }
    }

    /**
     * 从assets目录中复制某文件内容
     *
     * @param assetFileName assets目录下的Apk源文件路径
     * @param newFileName   复制到/data/data/package_name/files/目录下文件名
     */
    private void copyAssetsFileToAppFiles(String assetFileName, String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = this.getAssets().open(assetFileName);
            fos = this.openFileOutput(newFileName, Context.MODE_PRIVATE);
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
