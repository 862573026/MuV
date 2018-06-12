package com.newx.muv.ui.widget.combinetransfer.proxy;

import android.view.View;

import com.newx.base.frameworks.util.log.NXLog;
import com.newx.muv.ui.widget.combinetransfer.observer.BindProperty;

import static android.view.View.VISIBLE;

/**
 * Created by newx on 18-4-12.
 * 转换代理
 */

public class TransferProxy {

    /**
     * 透明度转换
     *
     * @param view
     * @param property
     * @param offset
     */
    public static void transferAlpha(View view, BindProperty property, int offset) {
        //快速滑动会导致问题
//        if (offset > property.startOffset && offset < property.endOffset) {

        float percent = (float) (offset - property.startOffset) / (property.endOffset - property.startOffset);

        if (property.endAlpha < property.startAlpha) { //Dim out
            float alpha = 1 - ((property.startAlpha - property.endAlpha) * percent);
            alpha = formatNum(alpha);
            view.setAlpha(alpha);
        } else { //dim in
            float alpha = property.startAlpha + (property.endAlpha - property.startAlpha) * percent;
            alpha = formatNum(alpha);
            view.setAlpha(alpha);
        }
    }

    public static void transferScale(View view, BindProperty property, int offset) {
        float percent = (float) (offset - property.startOffset) / (property.endOffset - property.startOffset);

        if (property.endScale < property.startScale) { //Scale out
            float scale = 1 - ((property.startScale - property.endScale) * percent);
            scale = formatNum(scale);
            view.setScaleX(scale);
            view.setScaleY(scale);
        } else { //Scale in
            float scale = property.startScale + (property.endScale - property.startScale) * percent;
            scale = formatNum(scale);
            view.setScaleX(scale);
            view.setScaleY(scale);
        }

//        if (view.getScaleX() >= 0 && view.getScaleY() >= 0) {
//            view.setVisibility(VISIBLE);
//        }
    }

    public static void initView(View view, BindProperty property) {
        view.setAlpha(property.startAlpha);
        view.setScaleX(property.startScale);
        view.setScaleY(property.startScale);
    }

    public static void isVisible(View view, BindProperty property) {
        if (view.getAlpha() > 0 && (view.getScaleX() >= 0 && view.getScaleY() >= 0)) {
            view.setVisibility(VISIBLE);
        }
    }

    public static float formatNum(float num) {
        if (num < 0) {
            return 0;
        } else if (num > 1) {
            return 1;
        }
        return num;
    }
}
