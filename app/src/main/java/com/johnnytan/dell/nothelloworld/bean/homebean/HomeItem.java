package com.johnnytan.dell.nothelloworld.bean.homebean;

/*
 * Created by JohnnyTan on 2018/3/18.
 */

import cn.bmob.v3.BmobObject;

public class HomeItem extends BmobObject{
    private String cardId;
    private String title;
    private int imageResource;

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}
