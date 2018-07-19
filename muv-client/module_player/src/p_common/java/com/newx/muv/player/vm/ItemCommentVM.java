package com.newx.muv.player.vm;


import com.newx.base.vm.NxBaseVM;
import com.newx.muv.player.entity.CommentEntity;

/**
 * Created by xuzhijian on 2018/4/19 0019.
 */

public class ItemCommentVM extends NxBaseVM {

    public CommentEntity mEntity;

    public ItemCommentVM(CommentEntity entity) {
        mEntity = entity;
    }
}
