package com.johnnytan.dell.nothelloworld.utils;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/*
 * Created by JohnnyTan on 2018/3/6.
 */

/*
利用反射机制解决滑动卡顿
* */

public class BottomNavigationHelper {
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView bottomNavigationView) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        try {
            Field mShiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");//利用反射机制进行设置
            mShiftingMode.setAccessible(true);
            mShiftingMode.setBoolean(menuView,false);
            mShiftingMode.setAccessible(false);

            for (int i = 0;i < menuView.getChildCount(); i++)
            {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(0);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());

            }

        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
