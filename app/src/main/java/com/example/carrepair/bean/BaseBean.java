package com.example.carrepair.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 *  请求基类
 */
public class BaseBean extends BmobObject implements Serializable {
    protected   long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
