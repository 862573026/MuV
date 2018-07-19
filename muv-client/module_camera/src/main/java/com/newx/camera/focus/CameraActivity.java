package com.newx.camera.focus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.newx.camera.R;
import com.newx.camera.databinding.ActivityCamera2Binding;
import com.newx.camera.focus.album.AlbumHelper;
import com.newx.camera.focus.album.ImageItem;
import com.newx.camera.focus.util.BitmapUtils;
import com.newx.camera.focus.util.ObjectManager;
import com.newx.camera.focus.widget.SquareCameraContainer;
import com.newx.common.frameworks.support.mvvm.NxBindingActivity;
import com.newx.utils.thread.ThreadUtil;

import java.util.List;


/**
 * 自定义相机的activity
 *
 * @author jerry
 * @date 2015-09-01
 */
public class CameraActivity extends NxBindingActivity<ActivityCamera2Binding> {

    private CameraManager mCameraManager;

    private SquareCameraContainer mCameraContainer;
    private ImageView ibRecentPic;

    private int mFinishCount = 2;   //finish计数   当动画和异步任务都结束的时候  再调用finish方法
    AlbumHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibRecentPic = mBinding.ibRecentPic;
        mCameraManager = CameraManager.getInstance(this);
        mCameraContainer = mBinding.cameraContainer;
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    void initData() {
        mCameraManager.bindOptionMenuView(mBinding.layoutOptions.tvFlashlight, mBinding.layoutOptions.tvCameraDirection);
        mCameraContainer.bindActivity(this);

        //todo  获取系统相册中的一张相片
        List<ImageItem> list = helper.getImagesList();
        if (list != null && list.size() != 0) {
            ibRecentPic.setImageBitmap(BitmapUtils.createCaptureBitmap(list.get(0).imagePath));
            ibRecentPic.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            //设置默认图片
            ibRecentPic.setImageResource(R.drawable.selector_camera_icon_album);
        }
    }

    /**
     * 点击闪关灯
     * @param view
     */
    public void clickFlashLight(View view){
        mCameraContainer.switchFlashMode();
    }

    /**
     * 点击摄像头切换
     * @param view
     */
    public void clickCameraDirection(View view){
        if (mCameraManager.canSwitch()) {
            mBinding.layoutOptions.tvCameraDirection.setClickable(false);
            mCameraContainer.switchCamera();

            //500ms后才能再次点击
            ThreadUtil.doInMainThread(o -> mBinding.layoutOptions.tvCameraDirection.setClickable(true),500);
        }
    }

    /**
     * 点击相册
     */
    public void clickRecentPic(View view){
        //跳转到系统相册
        Intent intent = new Intent(Intent.ACTION_DEFAULT,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mCameraContainer != null) {
            mCameraContainer.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mCameraContainer != null) {
            mCameraContainer.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraManager.unbinding();
        mCameraManager.releaseActivityCamera();
    }

    @Override
    public int initContentView() {
        return R.layout.activity_camera2;
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        //在创建前  释放相机
//    }

    /**
     * 对一些参数重置
     */
    public void reset() {
        mFinishCount = 2;
    }

    /**
     * 退出按钮点击
     */
    public void onExitClicked(View view) {
        onBackPressed();
    }

    /**
     * 照相按钮点击
     */
    public void onTakePhotoClicked(View view) {
        mCameraContainer.takePicture();
    }

    /**
     * 提交finish任务  进行计数  都在main Thread
     */

    /**
     * 照完照片 提交
     */
    public void postTakePhoto() {
//        mFinishCount--;
//        if (mFinishCount < 0) mFinishCount = 2;
//        if (mFinishCount == 0) {
//            setResult(RESULT_OK);
//            finish();
//        }
//        mCameraManager.releaseActivityCamera();

        Toast.makeText(this, "take photo", Toast.LENGTH_SHORT).show();

        Bitmap bitmap = ObjectManager.instance().getCameraBitmap();

        if (bitmap != null) {
            ibRecentPic.setImageBitmap(bitmap);
        }


    }
}
