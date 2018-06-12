package com.newx.muv.helper;

import android.databinding.BindingAdapter;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;
import com.newx.muv.base.databinding.command.BindingCommand;


/**
 * Created by xuzhijian on 2018/4/24 0024.
 */

public class OnClickHelper {

    @BindingAdapter("onClick")
    public static void onClick(View view, final BindingCommand clickCommand){
        RxView.clicks(view).subscribe(object -> {
            if (clickCommand != null) {
                clickCommand.execute();
            }
        });
    }



}
