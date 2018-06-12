package com.newx.camera.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.newx.android.arouter.routes.ARouter$$Group$$camera;
import com.newx.base.frameworks.route.launcher.ARouter;
import com.newx.base.frameworks.route.utils.ClassUtils;
import com.newx.base.frameworks.route.utils.Consts;
import com.newx.base.ui.fragmentation.SupportFragment;
import com.newx.base.utils.utilcode.constant.PermissionConstants;
import com.newx.base.utils.utilcode.subutil.PathUtils;
import com.newx.base.utils.utilcode.util.PermissionUtils;
import com.newx.camera.PathHelper;
import com.newx.camera.R;
import com.newx.muv.view.route.FRAGMENT;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;

import dalvik.system.DexFile;

/**
 * Created by xuzhijian on 2018/6/5 0005.
 */

public class FileActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {


//                        String file = "lib_camera.apk";
                        String file = "-1364671305.jar";
                        String apkPath = "external" + File.separator + file;
                        copyAssetsFileToAppFiles(apkPath, file);

                        fileOpt(file);

//                        String path = PathHelper.getRealPath();
//
//                        Log.e("NewX", "当前路径:" + path);

                        try {
                            List<String> list = ClassUtils.getSourcePaths(FileActivity.this);
                            Log.e("NewX", "list的长度" + list.size());

                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.e("NewX", "解析完毕");
                    }

                    @Override
                    public void onDenied() {

                    }
                })
                .request();

    }

    /**
     * 可以改进ClassUtils 从jar解析 routes
     *
     * @param file
     */
    private void fileOpt(String file) {
        String path = PathUtils.getAppIntFilesPath() + File.separator + file;
        try {
            DexFile dexfile = DexFile.loadDex(path, path + ".tmp", 0);

            Enumeration<String> dexEntries = dexfile.entries();

            final Set<String> classNames = new HashSet<>();

            while (dexEntries.hasMoreElements()) {
                String className = dexEntries.nextElement();
                if (className.startsWith(Consts.ROUTE_ROOT_PAKCAGE)) {
                    classNames.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
