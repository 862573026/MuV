package com.newx.muv.player;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.view.View;

import com.newx.media.video.base.GSYBaseVideoPlayer;
import com.newx.media.video.base.GSYVideoPlayer;
import com.newx.muv.player.def.PlayerSetting;
import com.newx.muv.player.helper.MediaHelper;
import com.newx.utils.def.DEFAULT;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by xuzhijian on 2018/4/24 0024.
 * 视频播放 管理
 */

public class VideoPlayerManager {


    private static VideoPlayerManagerBuilder mBuilder;


    @NonNull
    public static VideoPlayerManagerBuilder newBuilder(@NonNull GSYVideoPlayer videoPlayer) {
        mBuilder = new VideoPlayerManagerBuilder(videoPlayer);
        return mBuilder;
    }


    public static VideoPlayerManager getManager() {
        if (mManager != null) {
            return mManager;
        } else {
            throw new RuntimeException("请先设置当前的VideoPlayer");
        }
    }


    public String mUrl = DEFAULT.URL;

    private float mVolumePercent = 1;       //音量百分比(视屏的音量，不是AudioManager控制的)
    private int mSmoothExitDuration = 3000; //逐渐退出的时间
    private Disposable mSmoothExitTask; //逐渐退出的任务


    private GSYVideoPlayer mCurrentVideoView;

    private VideoPlayerManager(GSYVideoPlayer currentVideoView) {
        setVideoPlayer(currentVideoView);
    }

    private void setVideoPlayer(GSYVideoPlayer currentVideoView) {
        if (mSmoothExitTask != null) {
            mSmoothExitTask.dispose();
        }

        if (mCurrentVideoView != null && mCurrentVideoView.isInPlayingState()) {
            mVolumePercent = 1;
            mUrl = DEFAULT.URL;
            mCurrentVideoView.onVideoPause();
            mCurrentVideoView.release();
        }

        mCurrentVideoView = currentVideoView;
    }


    private void setUp(String url, boolean cacheWithPlay, File cachePath, String title) {
        mUrl = url;
        mCurrentVideoView.setUp(url, cacheWithPlay, cachePath, title);
    }


    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        return mCurrentVideoView.startWindowFullscreen(context, actionBar, statusBar);
    }

    public void startPrepared() {
        mCurrentVideoView.startPrepared();
    }

    public void pause() {
        if (mCurrentVideoView.isInPlayingState()) {
            mCurrentVideoView.onVideoPause();
        }
    }

    public void setVolume(@FloatRange(from = 0, to = 1) float volume) {
        if (mCurrentVideoView.isInPlayingState()) {
            mVolumePercent = volume;
            mCurrentVideoView.getGSYVideoManager().getMediaPlayer().setVolume(mVolumePercent, mVolumePercent);
        }
    }

    private float getVolumePercent() {
        return mVolumePercent;
    }


    /**
     * 低声退出
     */
    public void smoothExit() {
        if (mCurrentVideoView.isInPlayingState()) {
            MediaHelper.playedMedia.put(mUrl, mCurrentVideoView.getCurrentPositionWhenPlaying()); //存储一下

            mSmoothExitTask = Flowable.intervalRange(0, mSmoothExitDuration, 0, 1, TimeUnit.MILLISECONDS)
                    .onBackpressureDrop()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(new Consumer<Long>() {
                        @Override
                        public void accept(Long time) throws Exception {
                            float volume = getVolumePercent();
                            volume = volume - (1 / (float) mSmoothExitDuration);
                            setVolume(volume);
                        }
                    })
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Exception {
                            pause();
                            setVolume(1);
                        }
                    })
                    .subscribe();
        }

    }

    private volatile static VideoPlayerManager mManager;


    /**
     * 创建 VideoPlayerManager
     */
    public static class VideoPlayerManagerBuilder {

        private String url;
        private boolean cacheWithPlay = false;
        private File cachePath;
        private String title;
        private View thumb;
        private View.OnClickListener fullClick;
        private boolean continuePlay = false;


        public VideoPlayerManagerBuilder(GSYVideoPlayer currentVideoView) {

            //多线程注意
            if (mManager == null) {
                synchronized (VideoPlayerManager.class) {
                    if (mManager == null) {
                        mManager = new VideoPlayerManager(currentVideoView); //资源比较大只初始化一次
                    }
                }
            } else {
                mManager.setVideoPlayer(currentVideoView);
            }

        }

        public VideoPlayerManagerBuilder url(String url) {
            this.url = url;
            return this;
        }

        public VideoPlayerManagerBuilder title(String title) {
            this.title = title;
            return this;
        }

        public VideoPlayerManagerBuilder cacheWithPlay() {
            this.cacheWithPlay = true;
            return this;
        }

        public VideoPlayerManagerBuilder cachePath(File cachePath) {
            this.cachePath = cachePath;
            return this;
        }

        public VideoPlayerManagerBuilder thumb(View thumb) {
            this.thumb = thumb;
            return this;
        }

        public VideoPlayerManagerBuilder fullClick(View.OnClickListener fullClick) {
            this.fullClick = fullClick;
            return this;
        }

        public VideoPlayerManagerBuilder continuePlay() {
            this.continuePlay = true;
            return this;
        }


        public void build() {

            //自动续播功能
            if (PlayerSetting.useContinuePlay && continuePlay
                    && MediaHelper.playedMedia.containsKey(url)) {
                mManager.mCurrentVideoView.setSeekOnStart(MediaHelper.playedMedia.get(url));
            }

            mManager.setUp(url, cacheWithPlay, cachePath, title); //要存储URL
            mManager.mCurrentVideoView.setThumbImageView(thumb);
            mManager.mCurrentVideoView.getFullscreenButton().setOnClickListener(fullClick);
        }
    }

}
