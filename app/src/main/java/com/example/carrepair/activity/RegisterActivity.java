package com.example.carrepair.activity;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.carrepair.MainActivity;
import com.example.carrepair.R;
import com.example.carrepair.bean.User;
import com.example.carrepair.widget.ClearEditText;
import com.lidroid.xutils.view.annotation.ViewInject;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
/**
 * 用户注册
 */
public class RegisterActivity extends BaseActivity{
    @ViewInject(R.id.et_name)
    private ClearEditText mName;
    @ViewInject(R.id.et_pwd)
    private ClearEditText mPwd;
    User user = new User();

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void init() {

    }

    @Override
    public void setToolbar() {
        getToolbar().setRightButtonText("下一步");
        getToolbar().setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入的用户名
                String nameSt = mName.getText().toString();
                String pwdSt = mPwd.getText().toString();
                //bmob上传数据
                user.setUsername(nameSt);
                user.setPwd(pwdSt);
                user.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e == null){
                            Toast.makeText(RegisterActivity.this, "注册成功，请重新登录", Toast.LENGTH_SHORT).show();
                            Log.d("Bmob","添加数据成功！");
                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Log.d("Bmob","添加数据失败！");
                        }
                    }
                });

            }
        });

    }
}
