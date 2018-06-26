package com.newx.muv.entity.bo;

/**
 * Created by xuzhijian on 2018/6/26 0026.
 * 用户角色视图
 */
public class UserRoleInfo {

    private Integer userId;
    private Integer roleId;
    private String code;
    private String description;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
