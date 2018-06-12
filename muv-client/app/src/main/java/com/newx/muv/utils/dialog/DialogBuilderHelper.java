package com.newx.muv.utils.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.newx.base.frameworks.widget.toast.ToastUtil;
import com.newx.entity.local.ServersEntity;
import com.newx.muv.R;

import java.util.List;

/**
 * Created by xuzhijian on 2018/5/5 0005.
 */

public class DialogBuilderHelper {

    public static void showTipDialog(Context context, String message) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(R.string.tip_text)
                .content(message)
                .positiveText(R.string.ok_text);

        MaterialDialog dialog = builder.build();
        dialog.show();
    }

    public static void showMultiChoiceDialog(Context context, String title, List<String> list) {
        new MaterialDialog.Builder(context)
                .title(title)
                .widgetColor(Color.RED)
                .items(list)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        return false;
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        ToastUtil.showShort("选中的：" + dialog.getSelectedIndices());
                    }
                })
                .show();
    }

    /**
     * 单选
     *
     * @param context
     * @param selectedIndex
     * @param title
     * @param list
     * @param listCallbackSingleChoice
     */
    public static void showSingleChoiceDialog(Context context, int selectedIndex, String title, List<ServersEntity.ServerBean> list, MaterialDialog.ListCallbackSingleChoice listCallbackSingleChoice) {
        new MaterialDialog.Builder(context)
                .title(title)
                .widgetColor(Color.RED)
                .items(list)
                .positiveText(R.string.ok_text)
                .itemsCallbackSingleChoice(selectedIndex, listCallbackSingleChoice)
                .show();
    }

    public static MaterialDialog.Builder baseTipChooseDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .title(R.string.tip_text)
                .widgetColor(Color.RED)
                .positiveText(R.string.ok_text)
                .negativeText(R.string.cancel_text)
                .canceledOnTouchOutside(true);
    }
}
