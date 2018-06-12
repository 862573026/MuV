package com.newx.muv.vm;


import com.newx.entity.base.CommentEntity;
import com.newx.muv.base.page.NSBaseVM;

/**
 * Created by xuzhijian on 2018/4/19 0019.
 */

public class ItemCommentVM extends NSBaseVM {

    public CommentEntity mEntity;

    public ItemCommentVM(CommentEntity entity) {
        mEntity = entity;
    }
}
