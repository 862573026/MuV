package com.newx.ui.combinetransfer.observer;

import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;

import com.newx.ui.combinetransfer.base.BindDefine;
import com.newx.utils.object.ObjectUtil;


/**
 * Created by newx on 18-4-12.
 */

public class BindProperty {

    public int startOffset = 0;

    public int endOffset = BindDefine.DEFAULT_MAX_Y_OFFSET;

    public float startAlpha = 1;

    public float endAlpha = 1;

    public float startScale = 1;

    public float endScale = 1;


    private BindProperty() {

    }


    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }


    private BindProperty(@NonNull Builder builder) {
        ObjectUtil.checkNotNull(builder);

        startOffset = builder.startOffset;
        endOffset = builder.endOffset;
        startAlpha = builder.startAlpha;
        endAlpha = builder.endAlpha;
        startScale = builder.startScale;
        endScale = builder.endScale;
    }


    public static class Builder {

        int startOffset = 0;

        int endOffset = BindDefine.DEFAULT_MAX_Y_OFFSET;

        float startAlpha = 1;

        float endAlpha = 1;

        float startScale = 1;

        float endScale = 1;

        public Builder dimIn() {
            this.startAlpha = 0;
            this.endAlpha = 1;
            return this;
        }

        public Builder dimOut() {
            this.startAlpha = 1;
            this.endAlpha = 0;
            return this;
        }

        public Builder scaleIn() {
            this.startScale = 0;
            this.endScale = 1;
            return this;
        }

        public Builder scaleOut() {
            this.startScale = 1;
            this.endScale = 0;
            return this;
        }

        public Builder startOffset(int startOffset) {
            this.startOffset = startOffset;
            return this;
        }

        public Builder endOffset(int endOffset) {
            this.endOffset = endOffset;
            return this;
        }

        public Builder startAlpha(@FloatRange(from = 0.0, to = 1.0) float startAlpha) {
            this.startAlpha = startAlpha;
            return this;
        }

        public Builder endAlpha(@FloatRange(from = 0.0, to = 1.0) float endAlpha) {
            this.endAlpha = endAlpha;
            return this;
        }



        public BindProperty build() {
            return new BindProperty(this);
        }

    }


}
