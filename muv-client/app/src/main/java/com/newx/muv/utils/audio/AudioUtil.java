package com.newx.muv.utils.audio;

import android.content.Context;
import android.media.AudioManager;

import com.newx.base.proxy.ResourceProxy;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xuzhijian on 2018/4/24 0024.
 */

public class AudioUtil {

    private static AudioManager mAudioManager = (AudioManager) ResourceProxy.getContext().getSystemService(Context.AUDIO_SERVICE);

    public static void smoothLower(long delay, long period) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, current - 1, AudioManager.FX_KEY_CLICK);
            }
        }, delay, period);


    }


}
