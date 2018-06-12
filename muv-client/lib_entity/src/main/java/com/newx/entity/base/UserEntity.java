package com.newx.entity.base;

/**
 * Created by xuzhijian on 2018/4/19 0019.
 */

public class UserEntity implements EntityType {

    private String uId;

    private String uNickNamme;

    private String uIcon;

    private String uGender;

    private int uStarsNum;

    private int uFansNum;

    private int nWorksNum;

    private boolean nStar;

    @Override
    public int getEntityType() {
        return UserEntity;
    }
}
