package com.example.carrepair.utils;

import android.content.Context;
import android.text.TextUtils;

import com.example.carrepair.widget.Contants;
import com.example.carrepair.bean.User;

/**
 * 用户管理类
 */
public class UserLocalData {
    //存储用户数据
    public static void putUser(Context context,User user){


        String user_json =  JSONUtil.toJSON(user);
        PreferencesUtils.putString(context, Contants.USER_JSON,user_json);

    }
    //存储Token
    public static void putToken(Context context,String token){

        PreferencesUtils.putString(context, Contants.TOKEN,token);
    }

    //获取用户数据
    public static User getUser(Context context){

        String user_json= PreferencesUtils.getString(context,Contants.USER_JSON);
        if(!TextUtils.isEmpty(user_json)){

            return  JSONUtil.fromJson(user_json,User.class);
        }
        return  null;
    }
    //获取token
    public static  String getToken(Context context){

        return  PreferencesUtils.getString( context,Contants.TOKEN);

    }

    //清除用户数据
    public static void clearUser(Context context){


        PreferencesUtils.putString(context, Contants.USER_JSON,"");

    }
    //清除token
    public static void clearToken(Context context){

        PreferencesUtils.putString(context, Contants.TOKEN,"");
    }

}
