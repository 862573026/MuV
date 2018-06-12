package com.newx.muv.helper;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.newx.muv.R;

/**
 * Created by xuzhijian on 2018/4/11 0011.
 * 图片帮助类
 */

public class ImageHelper {

    /**
     * 加载图片
     *
     * @param iv
     * @param url
     */
    @BindingAdapter("imageUrl")
    public static void loadImage(final ImageView iv, String url) {
        Glide.with(iv.getContext())
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        iv.setBackgroundResource(R.mipmap.ic_launcher); //为什么原来简单的设置图片改成这样？我觉得是为了额外操作，比如做个回传错误图片，可以统计一下
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(iv);
    }

    /**
     * 加载mipmap
     *
     * @param iv
     * @param resId
     */
    @BindingAdapter("resId")
    public static void loadMipmapResource(ImageView iv, @DrawableRes int resId) {
        iv.setImageResource(resId);
    }

    /**
     * 加载资源
     *
     * @param iv
     * @param bitmap
     */
    @BindingAdapter("srcBitmap")
    public static void loadBackground(ImageView iv, Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

    /**
     * 加载背景
     *
     * @param view
     * @param url
     */
    @BindingAdapter("backgroundUrl")
    public static void loadBackground(final View view, String url) {
        SimpleTarget<Drawable> target = new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.setBackground(resource);
                } else {
                    view.setBackgroundDrawable(resource);
                }
            }
        };

        Glide.with(view.getContext())
                .load(url)
                .into(target);
    }

    /**
     * 加载背景
     *
     * @param view
     */
    @BindingAdapter("backgroundBitmap")
    public static void loadBackground(final View view, Bitmap bitmap) {
        SimpleTarget<Drawable> target = new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.setBackground(resource);
                } else {
                    view.setBackgroundDrawable(resource);
                }
            }
        };
    }
}
