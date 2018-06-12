package com.newx.muv.view.video;

import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.entity.base.VideoPreEntity;
import com.newx.muv.BR;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentVideoDetailBinding;
import com.newx.muv.base.def.Constant;
import com.newx.muv.base.def.PathDef;
import com.newx.muv.base.def.SystemSetting;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.proxy.VideoPlayerManager;
import com.newx.muv.vm.VideoDetailVM;


/**
 * Created by newx on 18-4-17.
 * 视频详情
 */
@Route(path = FRAGMENT.VideoDetailFragment)
public class VideoDetailFragment extends NxMvvMFragment<FragmentVideoDetailBinding, VideoDetailVM> {

    private VideoPreEntity mEntity;
    private int videoId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_video_detail;
    }

    @Override
    public void initParam() {
        Bundle mBundle = getArguments();
        if (mBundle != null) {
            mEntity = mBundle.getParcelable(Constant.BUNDLE_VIDEO_ENTITY);
        }

        videoId = Integer.parseInt(mEntity.getVideoId());
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

        mViewModel.uc.status.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (sender instanceof ObservableInt) {
                    ObservableInt status = (ObservableInt) sender;

                    switch (status.get()) {
                        case VideoDetailVM.STATUS_PREPARE:

                            ImageView imageView = new ImageView(VideoDetailFragment.this.getContext());
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            Glide.with(VideoDetailFragment.this.getContext())
                                    .load(mViewModel.mVideoEntity.getCoverUrl())
                                    .into(imageView);

                            VideoPlayerManager.newBuilder(mBinding.viewVideo)
                                    .url(mViewModel.mVideoEntity.getVideoUrl())
                                    .cacheWithPlay()
                                    .cachePath(PathDef.MEDIA_CACHE_DIR)
                                    .title(mViewModel.mVideoEntity.getTitle())
                                    .continuePlay()
                                    .fullClick(v -> VideoPlayerManager.getManager()
                                            .startWindowFullscreen(VideoDetailFragment.this.getContext(), false, false))
                                    .thumb(imageView)
                                    .build();


                            if (SystemSetting.useImmediatelyPlay) {
                                VideoPlayerManager.getManager().startPrepared();
                            }

                            break;

                        case VideoDetailVM.STATUS_PAUSE:

                            break;
                    }
                }

            }
        });

        mViewModel.uc.progress.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {

            }
        });

        mBinding.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                VideoPlayerManager.getInstance().pause();

//                int size = DisplayUtil.dip2px(ResourceProxy.getContext(), 200);
//
//                SmallVideoPlayerSetting setting = new SmallVideoPlayerSetting.Builder()
//                        .width(size)
//                        .height(size)
//                        .enableMove()
//                        .build();
//                mBinding.viewVideo.showSmallVideo(setting);

//                volume = volume - 0.1f;
//                VideoPlayerManager.getInstance().setVolume(volume, volume);
//                AudioUtil.smoothLower(0, 1000);
            }
        });

    }


    @Override
    public int initVariableId() {
        return BR.mVideoDetailModel;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mBinding.viewComments.setNestedScrollingEnabled(false);
        mBinding.listHotComment.setNestedScrollingEnabled(false);
        mBinding.listNewestComment.setNestedScrollingEnabled(false);
    }

    @Override
    public VideoDetailVM initVM() {
        return new VideoDetailVM(videoId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (SystemSetting.useSmoothExitMedia) {
            VideoPlayerManager.getManager().smoothExit();
        }
    }


//    @Override
//    protected boolean isImmersionBarEnabled() {
//        return true;
//    }
}
