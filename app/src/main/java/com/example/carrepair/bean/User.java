
package com.example.carrepair.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 用户
 */
public class User extends BmobObject implements Serializable {
    private Long id;
    private  String email;
    private  String logo_url;
    private  String username;
    private  String user_pwd;

    public User(){
    }

    public User(Long id, String email, String logo_url, String username, String user_pwd) {
        this.id = id;
        this.email = email;
        this.logo_url = logo_url;
        this.username = username;
        this.user_pwd = user_pwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return user_pwd;
    }

    public void setPwd(String mobi) {
        this.user_pwd = mobi;
    }
}
