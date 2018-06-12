package com.newx.muv.mapper;

import com.newx.entity.gen.DaoSession;
import com.newx.entity.gen.VideoInfoDao;
import com.newx.muv.ioc.ComponentHelper;
import com.newx.muv.utils.rx.rxbus.RxBus;

import org.greenrobot.greendao.AbstractDao;

import javax.inject.Inject;


/**
 * Created by xuzhijian on 2018/5/11 0011.
 * 数据库操作
 */

public abstract class BaseMapper {

    @Inject
    RxBus mRxBus;

    @Inject
    DaoSession mDaoSession;

    public BaseMapper() {
        ComponentHelper.getComponent()
                .injectMapper(this);

    }

}
