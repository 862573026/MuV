package com.newx.media.video.base;


import com.newx.entity.def.DEFAULT;

/**
 * Created by xuzhijian on 2018/4/25 0025.
 * 小视屏播放的设置
 */

public class SmallVideoPlayerSetting {

    public int width; //小视屏的宽
    public int height; //小视屏的高
    public int marginLeft; //左边距
    public int marginTop; //上边距
    public boolean hasActionBar; //有ActionBar
    public boolean hasStatusBar; //有StatusBar
    public boolean movable; //可移动
    public boolean clickable; //可点击

    public SmallVideoPlayerSetting(int width, int height, int marginLeft, int marginTop, boolean hasActionBar, boolean hasStatusBar, boolean movable, boolean clickble) {
        this.width = width;
        this.height = height;
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
        this.hasActionBar = hasActionBar;
        this.hasStatusBar = hasStatusBar;
        this.movable = movable;
        this.clickable = clickable;
    }

    public static class Builder {

        private int width = DEFAULT.LENGTH;
        private int height = DEFAULT.LENGTH;
        private int marginLeft = DEFAULT.LENGTH;
        private int marginTop = DEFAULT.LENGTH;
        private boolean hasActionBar = DEFAULT.BOOLEAN;
        private boolean hasStatusBar = DEFAULT.BOOLEAN;
        private boolean movable = DEFAULT.BOOLEAN;
        private boolean clickable = DEFAULT.BOOLEAN;

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder setMarginLeft(int marginLeft) {
            this.marginLeft = marginLeft;
            return this;
        }

        public Builder setMarginTop(int marginTop) {
            this.marginTop = marginTop;
            return this;
        }

        public Builder hasActionBar() {
            this.hasActionBar = true;
            return this;
        }

        public Builder hastatusBar() {
            this.hasStatusBar = true;
            return this;
        }

        public Builder enableMove() {
            this.movable = true;
            return this;
        }

        public Builder enableClick() {
            this.clickable = true;
            return this;
        }


        private SmallVideoPlayerSetting buildPlayer() {
            return new SmallVideoPlayerSetting(width, height, marginLeft, marginTop,
                    hasActionBar, hasStatusBar, movable, clickable);
        }

        public SmallVideoPlayerSetting build() {
            SmallVideoPlayerSetting player = buildPlayer();
            return player;
        }
    }
}
