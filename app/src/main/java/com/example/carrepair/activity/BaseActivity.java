package com.example.carrepair.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.example.carrepair.MyApplication;
import com.example.carrepair.R;
import com.example.carrepair.bean.User;
import com.example.carrepair.widget.CToolBar;
import com.lidroid.xutils.ViewUtils;

/**
 * BaseActivity封装
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ViewUtils.inject(this);
        initToolbar();
        init();
    }

    public abstract int getLayoutId();

    public abstract void init();

    private void initToolbar() {
        if (getToolbar() != null){
            setToolbar();
            getToolbar().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public CToolBar getToolbar() {
        return findViewById(R.id.toolbar);
    }
    public abstract void setToolbar();


    public void startActivity(Intent intent,boolean isNeedLogin){


        if(isNeedLogin){

            User user = MyApplication.getInstance().getUser();
            if(user !=null){
                super.startActivity(intent);
            }
            else{

                MyApplication.getInstance().putIntent(intent);
                Intent loginIntent = new Intent(this
                        , LoginActivity.class);
                super.startActivity(intent);

            }

        }
        else{
            super.startActivity(intent);
        }

    }
}
