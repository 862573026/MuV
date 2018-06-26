package com.newx.muv.entity.bo;

/**
 * Created by xuzhijian on 2018/6/26 0026.
 * Apk和插件联系
 */
public class ApkPlugin {

    private int id;
    private int apkId;
    private int pluginId;
    private boolean enable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApkId() {
        return apkId;
    }

    public void setApkId(int apkId) {
        this.apkId = apkId;
    }

    public int getPluginId() {
        return pluginId;
    }

    public void setPluginId(int pluginId) {
        this.pluginId = pluginId;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
