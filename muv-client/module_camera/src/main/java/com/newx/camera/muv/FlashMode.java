package com.newx.camera.muv;


/**
 * Created by xuzhijian on 2018/7/12 0012.
 */

public class FlashMode {

    private String mode;
    private int res;

    public FlashMode(String mode, int res) {
        this.mode = mode;
        this.res = res;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
