package com.johnnytan.dell.nothelloworld.bean;

import cn.bmob.v3.BmobObject;

/*
 * Created by JohnnyTan on 2018/3/15.
 */

public class User extends BmobObject{
    private String userName;
    private String userPwd;
    private String userEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
