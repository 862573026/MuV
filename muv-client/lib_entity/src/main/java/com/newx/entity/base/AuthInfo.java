package com.newx.entity.base;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by xuzhijian on 2018/5/11 0011.
 * 验证的信息
 */
@Entity
public class AuthInfo {

    @Id(autoincrement = true)
    private Long _id;

    private String uid;

    private String authorization;

    @Generated(hash = 853465194)
    public AuthInfo(Long _id, String uid, String authorization) {
        this._id = _id;
        this.uid = uid;
        this.authorization = authorization;
    }

    @Generated(hash = 1794305645)
    public AuthInfo() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
    
}
