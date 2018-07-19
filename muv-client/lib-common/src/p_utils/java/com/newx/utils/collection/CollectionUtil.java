package com.newx.utils.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xuzhijian on 2018/4/3 0003.
 *
 */

public class CollectionUtil<K, V> {

    private volatile static CollectionUtil mInstance;

    private CollectionUtil() {
    }

    public static CollectionUtil getInstance() {
        if (mInstance == null) {
            synchronized (CollectionUtil.class) {
                if (mInstance == null) {
                    mInstance = new CollectionUtil();
                }
            }
        }
        return mInstance;
    }

    public List<V> map2List(Map<K, V> map) {
        List<V> list = new ArrayList<>(map.values());
        return list;
    }
}
