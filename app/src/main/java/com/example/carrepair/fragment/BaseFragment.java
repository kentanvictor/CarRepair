package com.example.carrepair.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.carrepair.MyApplication;
import com.example.carrepair.activity.LoginActivity;
import com.example.carrepair.bean.User;
import com.lidroid.xutils.ViewUtils;

/**
 * BaseFragment
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = createView(inflater,container,savedInstanceState);
        ViewUtils.inject(this, view);
        initToolBar();
        init();
        return view;

    }

    public void  initToolBar(){

    }


    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void init();



    public void startActivity(Intent intent,boolean isNeedLogin){


        if(isNeedLogin){

            User user = MyApplication.getInstance().getUser();
            if(user !=null){
                super.startActivity(intent);
            }
            else{

                MyApplication.getInstance().putIntent(intent);// 保留意图
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                super.startActivity(loginIntent);

            }

        }
        else{
            super.startActivity(intent);
        }

    }


}
