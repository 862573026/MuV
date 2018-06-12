package com.newx.entity.base;

/**
 * Created by xuzhijian on 2018/4/19 0019.
 * 追评
 */

public class CommentReplayEntity implements EntityType{

    private String commentId;

    private String replyId;

    private String fromUId; //回复人

    private String fromUName;

    private String toUId; //回复对象

    private String toUName;

    private String reply; // 追评内容

    private String replyTime;


    @Override
    public int getEntityType() {
        return FollowCommentEntity;
    }
}
