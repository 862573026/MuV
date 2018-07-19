package com.newx.common.helper;

import android.content.Context;
import com.afollestad.materialdialogs.MaterialDialog;
import com.newx.common.R;


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


}
