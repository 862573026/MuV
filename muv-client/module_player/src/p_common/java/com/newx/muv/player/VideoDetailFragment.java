package com.newx.muv.player;

import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.player.def.PlayerPath;
import com.newx.muv.player.def.PlayerSetting;
import com.newx.muv.player.vm.VideoDetailVM;
import com.newx.player.BR;
import com.newx.player.R;
import com.newx.player.databinding.FragmentVideoDetailBinding;
import com.newx.player.def.PlayerConstant;
import com.newx.player.entity.VideoPreEntity;


/**
 * Created by newx on 18-4-17.
 * 视频详情
 */
@Route(path = RP.VideoDetailFragment)
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
            mEntity = mBundle.getParcelable(PlayerConstant.BUNDLE_VIDEO_ENTITY);
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
                                    .cachePath(PlayerPath.MEDIA_CACHE_DIR)
                                    .title(mViewModel.mVideoEntity.getTitle())
                                    .continuePlay()
                                    .fullClick(v -> VideoPlayerManager.getManager()
                                            .startWindowFullscreen(VideoDetailFragment.this.getContext(), false, false))
                                    .thumb(imageView)
                                    .build();


                            if (PlayerSetting.useImmediatelyPlay) {
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

        if (PlayerSetting.useSmoothExitMedia) {
            VideoPlayerManager.getManager().smoothExit();
        }
    }


//    @Override
//    protected boolean isImmersionBarEnabled() {
//        return true;
//    }
}
