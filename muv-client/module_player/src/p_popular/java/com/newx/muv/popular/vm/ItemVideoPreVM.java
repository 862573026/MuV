package com.newx.muv.popular.vm;

import android.os.Bundle;

import com.newx.base.route.RP;
import com.newx.base.vm.NxBaseVM;
import com.newx.common.command.BindingAction;
import com.newx.common.command.BindingCommand;
import com.newx.common.helper.JumpHelper;
import com.newx.player.def.PlayerConstant;
import com.newx.player.entity.VideoPreEntity;

/**
 * Created by xuzhijian on 2018/4/17 0017.
 * 视频预览 Item
 */

public class ItemVideoPreVM extends NxBaseVM {

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
            bundle.putParcelable(PlayerConstant.BUNDLE_VIDEO_ENTITY, mEntity);

            JumpHelper.fragment(RP.VideoDetailFragment,bundle)
                    .navigation();
        }
    });

}
