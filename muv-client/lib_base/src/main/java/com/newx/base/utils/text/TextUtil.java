package com.newx.base.utils.text;

import android.text.TextUtils;

import com.newx.entity.def.INVALID;


/**
 * Created by xuzhijian on 2018/3/27 0027.
 */

public class TextUtil {

    public static boolean isEmpty(String... strings) {
        boolean isEmpty = false;
        for (String temp : strings) {
            isEmpty = TextUtils.isEmpty(temp);
            if (isEmpty) {
                return true;
            }
        }
        return isEmpty;
    }

    public static boolean isInvalidNum(Integer... integer) {
        boolean isEmpty = false;
        for (int temp : integer) {
            isEmpty = (temp == INVALID.INTEGER) ? true : false;
            if (isEmpty) {
                return true;
            }
        }
        return isEmpty;
    }
}
