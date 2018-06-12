package com.newx.base.config;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by xuzhijian on 2018/4/19 0019.
 */

public class AssetsManager {
    //单例
    private volatile static AssetsManager mInstance = null;

    private AssetsManager(){

    }


    public static AssetsManager getInstance() {
        if (mInstance == null) {
            synchronized (AssetsManager.class) {
                if (mInstance == null) {
                    mInstance = new AssetsManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 从assets目录下读取文件
     *
     * @param context 上下文
     * @param fileName 文件名
     * @return
     */
    public String readFileFromAssets(Context context, String fileName) {
        String result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null)
                result += line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
