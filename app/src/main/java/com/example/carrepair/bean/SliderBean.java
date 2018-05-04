package com.example.carrepair.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 20304 on 2018/5/4.
 */

public class SliderBean extends BmobObject{
    private String ImageUrl;
    private String ImageText;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageText() {
        return ImageText;
    }

    public void setImageText(String imageText) {
        ImageText = imageText;
    }
}
