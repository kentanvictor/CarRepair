package com.example.carrepair.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 20304 on 2018/5/5.
 */

public class HomeBean extends BmobObject {
    private String homeTitle;
    private String homeDes;

    public String getHomeTitle() {
        return homeTitle;
    }

    public void setHomeTitle(String homeTitle) {
        this.homeTitle = homeTitle;
    }

    public String getHomeDes() {
        return homeDes;
    }

    public void setHomeDes(String homeDes) {
        this.homeDes = homeDes;
    }
}
