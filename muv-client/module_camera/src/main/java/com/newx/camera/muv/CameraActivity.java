package com.newx.camera.muv;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.newx.camera.R;
import com.newx.camera.databinding.ActivityCameraBinding;
import com.newx.camera.def.CameraDef;
import com.newx.common.frameworks.support.mvvm.NxBindingActivity;
import com.newx.common.utils.ToastUtil;
import com.newx.utils.utilcode.constant.PermissionConstants;
import com.newx.utils.utilcode.util.PermissionUtils;

import org.wysaid.myUtils.ImageUtil;
import org.wysaid.view.CameraRecordGLSurfaceView;


/**
 * Created by xuzhijian on 2018/7/12 0012.
 * 相机
 */

public class CameraActivity extends NxBindingActivity<ActivityCameraBinding> {

    private CameraRecordGLSurfaceView mViewCamera;
    private CameraManager mCameraManager;
    private ConstraintLayout mLayoutFilter;
    private RelativeLayout mLayoutShot;

    @Override
    public int initContentView() {
        return R.layout.activity_camera;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        mViewCamera = mBinding.viewCamera;
                        mLayoutFilter = mBinding.layoutFilter.layoutContainer;
                        mLayoutShot = mBinding.layoutShot.layoutContainer;
                        mCameraManager = new CameraManager(mViewCamera, mBinding.btnFlash, mBinding.btnSwitch);
                        mViewCamera.setFitFullView(true); //设置全屏
                        initListener();
                    }

                    @Override
                    public void onDenied() {

                    }
                })
                .request();


    }

    /**
     * 点击闪光灯
     *
     * @param view
     */
    public void clickFlash(View view) {
        mCameraManager.setFlashMode();
    }

    /**
     * 点击镜头切换
     *
     * @param view
     */
    public void clickSwitch(View view) {
        mCameraManager.switchCamera();
    }

    /**
     * 点击滤镜
     *
     * @param view
     */
    public void clickFilter(View view) {
        ToastUtil.showShort("点击了滤镜");
//
        mLayoutFilter.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom));
        mLayoutShot.setAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_out_center));
        mLayoutShot.setVisibility(View.GONE);
        mLayoutFilter.setVisibility(View.VISIBLE);
    }

    /**
     * 照相按钮点击
     */
    public void onTakePhotoClicked(View view) {
        mViewCamera.takePicture(bmp -> {
            if (bmp != null) {
                String s = ImageUtil.saveBitmap(bmp);
                bmp.recycle();
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + s)));
            }
        }, null, CameraDef.EFFECT_CONFIGS[0], 1.0f, true);
    }


    /**
     * 退出按钮点击
     */
    public void onExitClicked(View view) {
        onBackPressed();
    }

    /**
     * 点击相册
     */
    public void clickRecentPic(View view) {
        //跳转到系统相册
        Intent intent = new Intent(Intent.ACTION_DEFAULT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivity(intent);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initListener() {
        mViewCamera.setOnTouchListener((v, event) -> {

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN: {
                    if (mLayoutFilter.getVisibility() == View.VISIBLE) {
                        mLayoutFilter.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom));
                        mLayoutFilter.setVisibility(View.GONE);
                        mLayoutShot.setAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in_center));
                        mLayoutShot.setVisibility(View.VISIBLE);
                    } else {
                        final float focusX = event.getX() / mViewCamera.getWidth();
                        final float focusY = event.getY() / mViewCamera.getHeight();

                        mViewCamera.focusAtPoint(focusX, focusY, (success, camera) -> {
                            if (success) {
                            } else {
                                mViewCamera.cameraInstance().setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
                            }
                        });
                    }
                }
                break;
                default:
                    break;
            }

            return true;
        });
    }

}
