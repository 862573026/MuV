package com.newx.camera.muv;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.newx.camera.R;
import com.newx.camera.def.*;
import com.newx.common.utils.CycleList;
import com.newx.utils.mobile.SPUtil;
import com.newx.utils.thread.ThreadUtil;

import org.wysaid.view.CameraRecordGLSurfaceView;

/**
 * Created by xuzhijian on 2018/7/12 0012.
 */

public class CameraManager {

    private CameraRecordGLSurfaceView mCameraView;
    private TextView mBtnFlash;
    private TextView mBtnSwitch;

    private final int DELAY_TIME = 500;
    private final String FLASH_MODE_AUTO = Camera.Parameters.FLASH_MODE_AUTO;
    private final String FLASH_MODE_ON = Camera.Parameters.FLASH_MODE_ON;
    private final String FLASH_MODE_OFF = Camera.Parameters.FLASH_MODE_OFF;

    private CycleList<String> mFlashModeList = new CycleList();


    public CameraManager(CameraRecordGLSurfaceView cameraView, TextView btnFlash, TextView btnSwitch) {
        mCameraView = cameraView;
        this.mBtnFlash = btnFlash;
        this.mBtnSwitch = btnSwitch;

        init();
    }

    public void init() {
        boolean isBackForward = SPUtil.getBoolean(CameraKey.IS_CAMERA_BACK, true);
        String flashMode = SPUtil.getString(CameraKey.LIGHT_MODE, Camera.Parameters.FLASH_MODE_AUTO);

        mFlashModeList.add(FLASH_MODE_AUTO);
        mFlashModeList.add(FLASH_MODE_ON);
        mFlashModeList.add(FLASH_MODE_OFF);

        int index = mFlashModeList.indexOf(flashMode);
        mFlashModeList.setIndex(index);

        mCameraView.presetCameraForward(isBackForward);
        mCameraView.setFlashLightMode(flashMode);
        setSwitchBtnBackground(isBackForward);
        setFlashBtnBackground(flashMode);

    }

    public void setFlashMode() {
        mBtnSwitch.setEnabled(false);
        String flashMode = mFlashModeList.next();
        SPUtil.put(CameraKey.LIGHT_MODE, flashMode);
        setFlashBtnBackground(flashMode);
        mCameraView.setFlashLightMode(flashMode);
        ThreadUtil.doInMainThread(o -> mBtnSwitch.setEnabled(true), DELAY_TIME);
    }

    public void switchCamera() {
        mBtnSwitch.setEnabled(false);
        boolean isBackForward = !mCameraView.isCameraBackForward();
        SPUtil.put(CameraKey.IS_CAMERA_BACK, isBackForward);
        setSwitchBtnBackground(isBackForward);
        mCameraView.switchCamera();
        ThreadUtil.doInMainThread(o -> mBtnSwitch.setEnabled(true), DELAY_TIME);
    }

    private void setSwitchBtnBackground(boolean isBackForward){
        mBtnSwitch.setBackgroundResource(isBackForward ? R.drawable.selector_btn_camera_back : R.drawable.selector_btn_camera_front);
        mBtnFlash.setVisibility(isBackForward ? View.VISIBLE : View.GONE);
    }

    private void setFlashBtnBackground(String mode) {
        switch (mode) {
            case FLASH_MODE_AUTO:
                mBtnFlash.setBackgroundResource(R.drawable.selector_btn_flashlight_auto);
                break;
            case FLASH_MODE_ON:
                mBtnFlash.setBackgroundResource(R.drawable.selector_btn_flashlight_on);
                break;
            case FLASH_MODE_OFF:
                mBtnFlash.setBackgroundResource(R.drawable.selector_btn_flashlight_off);
                break;
        }
    }

}
