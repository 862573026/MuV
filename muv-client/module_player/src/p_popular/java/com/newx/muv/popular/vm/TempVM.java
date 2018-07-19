package com.newx.muv.popular.vm;

import android.content.Context;

import com.newx.base.route.RP;
import com.newx.base.vm.NxBaseVM;
import com.newx.common.helper.JumpHelper;

/**
 * Created by xuzhijian on 2018/4/17 0017.
 */

public class TempVM extends NxBaseVM {

    private Context mContext;

    public TempVM(Context context) {
        mContext = context;
    }

    public void toLogin() {
        JumpHelper.fragment(RP.LoginChooseFragment)
                .navigation();
    }
}
