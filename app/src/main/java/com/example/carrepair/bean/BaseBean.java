package com.example.carrepair.bean;

import java.io.Serializable;

/**
 *  请求基类
 */
public class BaseBean implements Serializable {
    protected   long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
