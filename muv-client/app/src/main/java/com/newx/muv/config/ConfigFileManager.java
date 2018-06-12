package com.newx.muv.config;

import android.content.Context;

import com.google.gson.JsonObject;
import com.newx.muv.utils.text.GsonUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuzhijian on 2018/3/26 0026.
 * 配置文件管理类
 */

public class ConfigFileManager {

    //单例
    private volatile static ConfigFileManager mInstance = null;

    private Context mContext;

    private String mVersion = "0.0.0";

    private final String FILE_PAGE_REGISTER = "page_register.json";
    private final String FILE_FUNCTION = "function.json";


    /**
     * 页面Map
     */



    /**
     * 功能Map
     */
    public Map<String,String> mFunctionMap = new HashMap<>();

    private ConfigFileManager(){

    }


    /**
     * 获得单例
     *
     * @return PageManager 单例
     */
    public static ConfigFileManager getInstance() {
        if (mInstance == null) {
            synchronized (ConfigFileManager.class) {
                if (mInstance == null) {
                    mInstance = new ConfigFileManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化配置 - 从配置文件初始化
     *
     * @param context 上下文
     */
    public void init(Context context) {
        try {
            mContext = context.getApplicationContext();
            //解析 页面json
//            readPageConfig(readFileFromAssets(mContext, FILE_PAGE_REGISTER));
            //解析 功能开关json
            readFuntionConfig(readFileFromAssets(mContext, FILE_FUNCTION));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化 - 自定义初始化(可以用网络动态修改) -> 但是好像也不能很方便的增删注册的页面
     * @param context
     * @param pageJson
     */
    public void init(Context context,String pageJson) {
        this.init(context);
//        readPageConfig(pageJson);
    }

    /**
     * 从assets目录下读取文件
     *
     * @param context 上下文
     * @param fileName 文件名
     * @return
     */
    private String readFileFromAssets(Context context, String fileName) {
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

    /**
     * 读取页面配置文件
     * @param content
     */
//    private void readPageConfig(String content){
//        JsonObject jsonObject = GsonUtil.toJsonObject(content);
//
//        mVersion = jsonObject.get(Key.VERSION).getAsString();
//        //解析 Page
//        JsonArray pageArray = jsonObject.getAsJsonArray(Key.PAGE);
//        Iterator<JsonElement> pageIterator = pageArray.iterator();
//        JsonObject page = null;
//        PageModel pageModel = null;
//        while (pageIterator.hasNext()) {
//            page = (JsonObject) pageIterator.next();
//            pageModel = GsonUtil.toBean(page.toString(),PageModel.class);
//            mPageMap.put(page.get(Key.PAGE_ID).getAsInt(),pageModel);
//        }
//
//        //解析 PageGroup
//        JsonArray pageGroupArray = jsonObject.getAsJsonArray(Key.PAGE_GROUP);
//        Iterator<JsonElement> pageGroupIterator = pageGroupArray.iterator();
//        JsonObject pageGroup = null;
//        PageGroupModel pageGroupModel = null;
//        while (pageGroupIterator.hasNext()) {
//            pageGroup = (JsonObject) pageGroupIterator.next();
//            pageGroupModel = GsonUtil.toBean(pageGroup.toString(),PageGroupModel.class);
//            mPageGroupMap.put(pageGroup.get(Key.GROUP_ID).getAsInt(),pageGroupModel);
//        }
//
//    }

    /**
     * 读取功能配置文件
     * @param content
     */
    private void readFuntionConfig(String content){
        JsonObject jsonObject = GsonUtil.toJsonObject(content);

        mVersion = jsonObject.get(Key.VERSION).getAsString();
    }

    public static class Key {
        public static final String VERSION       = "version";

        public static final String PAGE          = "page";
        public static final String PAGE_GROUP    = "pageGroup";

        public static final String PAGE_ID       = "pageId";
        public static final String GROUP_ID      = "groupId";


    }


}
