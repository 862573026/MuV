package com.newx.camera.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.support.mvvm.NxBindingFragment;
import com.newx.camera.GPUImageFilterTools;
import com.newx.camera.R;
import com.newx.camera.databinding.FragmentCameraBinding;
import com.newx.camera.utils.CameraHelper;
import com.newx.muv.view.route.FRAGMENT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

/**
 * Created by xuzhijian on 2018/6/5 0005.
 */
@Route(path = FRAGMENT.FragmentCamera)
public class FragmentCamera extends NxBindingFragment<FragmentCameraBinding> {

    private GPUImage mGPUImage;
    private CameraHelper mCameraHelper;
    private CameraLoader mCamera;
    private GPUImageFilter mFilter;
    private GPUImageFilterTools.FilterAdjuster mFilterAdjuster;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    @Override
    public int initContentView() {
        return R.layout.fragment_camera;
    }

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);

        mGPUImage = new GPUImage(getContext());
        mGPUImage.setGLSurfaceView(mBinding.surfaceView);

        mCameraHelper = new CameraHelper(getContext());
        mCamera = new CameraLoader();

        mBinding.btnSwitchCamera.setOnClickListener(v -> mCamera.switchCamera());
        if (!mCameraHelper.hasFrontCamera() || !mCameraHelper.hasBackCamera()) {
            mBinding.btnSwitchCamera.setVisibility(View.GONE);
        }

        mBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mFilterAdjuster != null) {
                    mFilterAdjuster.adjust(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mBinding.btnChooseFilter.setOnClickListener(
                v -> GPUImageFilterTools.showDialog(getContext(),
                        filter -> switchFilterTo(filter)));

        mBinding.btnCapture.setOnClickListener(v -> {
            if (mCamera.mCameraInstance.getParameters().getFocusMode().equals(
                    Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                takePicture();
            } else {
                mCamera.mCameraInstance.autoFocus((success, camera) -> takePicture());
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        mCamera.onResume();
    }

    @Override
    public void onPause() {
        mCamera.onPause();
        super.onPause();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    private void switchFilterTo(final GPUImageFilter filter) {
        if (mFilter == null
                || (filter != null && !mFilter.getClass().equals(filter.getClass()))) {
            mFilter = filter;
            mGPUImage.setFilter(mFilter);
            mFilterAdjuster = new GPUImageFilterTools.FilterAdjuster(mFilter);
        }
    }


    private void takePicture() {
        // TODO get a size that is about the size of the screen
        Camera.Parameters params = mCamera.mCameraInstance.getParameters();
        params.setRotation(90);
        for (Camera.Size size : params.getSupportedPictureSizes()) {
            Log.i("ASDF", "Supported: " + size.width + "x" + size.height);
            params.setPictureSize(size.width,size.height);
        }
        mCamera.mCameraInstance.setParameters(params); //熟悉下Camera的Api,很多参数可以自定义
        mCamera.mCameraInstance.takePicture(null, null,
                (data, camera) -> {

                    final File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
                    if (pictureFile == null) {
                        Log.d("ASDF",
                                "Error creating media file, check storage permissions");
                        return;
                    }

                    try {
                        FileOutputStream fos = new FileOutputStream(pictureFile);
                        fos.write(data);
                        fos.close();
                    } catch (FileNotFoundException e) {
                        Log.d("ASDF", "File not found: " + e.getMessage());
                    } catch (IOException e) {
                        Log.d("ASDF", "Error accessing file: " + e.getMessage());
                    }

                    data = null;
                    Bitmap bitmap = BitmapFactory.decodeFile(pictureFile.getAbsolutePath());
                    // mGPUImage.setImage(bitmap);
                    final GLSurfaceView view = (GLSurfaceView) findViewById(R.id.surfaceView);
                    view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
                    mGPUImage.saveToPictures(bitmap, "GPUImage",
                            System.currentTimeMillis() + ".jpg",
                            uri -> {
                                pictureFile.delete();
                                camera.startPreview();
                                view.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
                            });
                });
    }

    private static File getOutputMediaFile(final int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    private class CameraLoader {

        private int mCurrentCameraId = 0;
        private Camera mCameraInstance;

        public void onResume() {
            setUpCamera(mCurrentCameraId);
        }

        public void onPause() {
            releaseCamera();
        }

        public void switchCamera() {
            releaseCamera();
            mCurrentCameraId = (mCurrentCameraId + 1) % mCameraHelper.getNumberOfCameras();
            setUpCamera(mCurrentCameraId);
        }

        private void setUpCamera(final int id) {
            mCameraInstance = getCameraInstance(id);
            Camera.Parameters parameters = mCameraInstance.getParameters();
            // TODO adjust by getting supportedPreviewSizes and then choosing
            // the best one for screen size (best fill screen)
            if (parameters.getSupportedFocusModes().contains(
                    Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            }
            mCameraInstance.setParameters(parameters);

            int orientation = mCameraHelper.getCameraDisplayOrientation(
                    getActivity(), mCurrentCameraId);
            CameraHelper.CameraInfo2 cameraInfo = new CameraHelper.CameraInfo2();
            mCameraHelper.getCameraInfo(mCurrentCameraId, cameraInfo);
            boolean flipHorizontal = cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT;
            mGPUImage.setUpCamera(mCameraInstance, orientation, flipHorizontal, false);
        }

        /** A safe way to get an instance of the Camera object. */
        private Camera getCameraInstance(final int id) {
            Camera c = null;
            try {
                c = mCameraHelper.openCamera(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return c;
        }

        private void releaseCamera() {
            mCameraInstance.setPreviewCallback(null);
            mCameraInstance.release();
            mCameraInstance = null;
        }
    }
}
