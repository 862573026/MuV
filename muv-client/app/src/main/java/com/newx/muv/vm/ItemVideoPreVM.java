package com.newx.muv.vm;

import android.os.Bundle;

import com.newx.entity.base.VideoPreEntity;
import com.newx.muv.base.databinding.command.BindingAction;
import com.newx.muv.base.databinding.command.BindingCommand;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.base.def.Constant;
import com.newx.muv.helper.JumpHelper;
import com.newx.muv.view.route.FRAGMENT;

/**
 * Created by xuzhijian on 2018/4/17 0017.
 * 视频预览 Item
 */

public class ItemVideoPreVM extends NSBaseVM {

    public VideoPreEntity mEntity;

    public ItemVideoPreVM(VideoPreEntity entity) {
        mEntity = entity;
    }


    //条目的点击事件
    public BindingCommand itemClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //这里可以通过一个标识,做出判断，已达到跳入不能界面的逻辑
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.BUNDLE_VIDEO_ENTITY, mEntity);

            JumpHelper.fragment(FRAGMENT.VideoDetailFragment,bundle)
                    .navigation();
        }
    });

}
