package com.newx.entity.base;

/**
 * Created by xuzhijian on 2018/4/11 0011.
 * Entity 类型  -> 考虑用注解实现，可以检查
 */

public interface EntityType {

    public static final int EntityType                                  = 10000;

    public static final int UserEntity                                  = EntityType + 1;

    public static final int VideoPreEntity                              = EntityType + 2;

    public static final int CommentEntity                               = EntityType + 3;

    public static final int FollowCommentEntity                         = EntityType + 4;


    /**
     * 获取实例类型
     * @return
     */
    public int getEntityType();
}
