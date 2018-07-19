package com.newx.user.entity;

/**
 * Created by newx on 18-5-12.
 */

public class LoginResult {


    private String jwt;
    private User user;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
