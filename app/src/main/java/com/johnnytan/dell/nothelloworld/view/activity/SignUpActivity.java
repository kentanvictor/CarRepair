package com.johnnytan.dell.nothelloworld.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.johnnytan.dell.nothelloworld.utils.EidtTextClearTools;
import com.johnnytan.dell.nothelloworld.R;
import com.johnnytan.dell.nothelloworld.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/*
 * Created by JohnnyTan on 2018/3/16.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mUserName;
    private EditText mPassWord;
    private EditText mEmail;
    private ImageView mNameClear;
    private ImageView mPwdClear;
    private ImageView mEmailClear;
    private Button mSignUp;
    private EidtTextClearTools clearTools;
    private User user;
    private String mName;
    private String mPwd;
    private String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        mSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.su_btn_signup:
                mName = mUserName.getText().toString();
                mPwd = mPassWord.getText().toString();
                email = mEmail.getText().toString();
                user.setUserName(mName);
                user.setUserEmail(email);
                user.setUserPwd(mPwd);
                user.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e == null)
                        {
                            Log.d("Signup","添加数据成功，返回objectId为:"+objectId);
                        }
                        else {
                            Log.d("Signup","创建数据失败：" + e.getMessage());
                        }
                    }
                });
        }
    }

    private void init() {
        mUserName = findViewById(R.id.su_et_userName);
        mPassWord = findViewById(R.id.su_et_password);
        mEmail = findViewById(R.id.su_et_userEmial);
        mNameClear = findViewById(R.id.su_iv_nameClear);
        mPwdClear = findViewById(R.id.su_iv_pwdClear);
        mEmailClear = findViewById(R.id.su_iv_emailClear);
        mSignUp = findViewById(R.id.su_btn_signup);
        clearTools = new EidtTextClearTools();
        user = new User();
        clearTools.addClearListener(mEmail, mEmailClear);
        clearTools.addClearListener(mUserName, mNameClear);
        clearTools.addClearListener(mPassWord, mPwdClear);
    }

    public void toast(String printInfo) {
        Toast.makeText(this, printInfo, Toast.LENGTH_SHORT).show();
    }
}
