package com.example.carrepair.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.carrepair.MainActivity;
import com.example.carrepair.MyApplication;
import com.example.carrepair.R;
import com.example.carrepair.bean.User;
import com.example.carrepair.utils.ToastUtils;
import com.example.carrepair.widget.CToolBar;
import com.example.carrepair.widget.ClearEditText;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;

import cn.bmob.v3.listener.FindListener;


public class LoginActivity extends AppCompatActivity {

    @ViewInject(R.id.toolbar_cn)
    private CToolBar mToolBar;
    @ViewInject(R.id.etxt_phone)
    private ClearEditText mEtxtPhone;
    @ViewInject(R.id.etxt_pwd)
    private ClearEditText mEtxtPwd;

    @ViewInject(R.id.btn_login)
    private Button loginButton;

    private BmobQuery<User> query = new BmobQuery<User>();
    public static boolean isLogin = false;
    public static User user;
    public static String root = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        initToolBar();
    }


    private void initToolBar() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    @OnClick(R.id.btn_login)
    public void login(View view) {

        //拿到用户名
        final String email = mEtxtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            ToastUtils.show(this, "请输入用户名");
            return;
        }

        //拿到密码号码并判断
        final String pwd = mEtxtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.show(this, "请输入密码");
            return;
        }


        Log.d("test", "test: " + email);
        //用于用户是否登录查询
        query.addWhereEqualTo("username", email);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null) {
                    if (list.get(0).getEmail().equals(root) && list.get(0).getPwd().equals(root)) {
                        user = list.get(0);
                        //root用户保存本地
                        MyApplication root = MyApplication.getInstance();
                        root.putUser(user, list.get(0).getPwd());
                        //管理员登录，跳转到修改数据界面。
                        Intent intent = new Intent(LoginActivity.this, RootActivity.class);
                        startActivity(intent);
                    }
                    if (list.size() != 0) {
                        Log.d("password test", "done: " + list.get(0).getPwd());
                        if (list.get(0).getPwd().equals(pwd)) {
                            /**
                             * 保存用户数据
                             */
                            user = list.get(0);
                            MyApplication application = MyApplication.getInstance();
                            application.putUser(user, list.get(0).getPwd());//将用户数据保存在本地
                            ToastUtils.show(LoginActivity.this, "登陆成功");
                            /**
                             * 登录成功后进行页面跳转
                             * */
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            isLogin = true;
                        } else {
                            ToastUtils.show(LoginActivity.this, "密码错误");
                        }
                    } else {
                        ToastUtils.show(LoginActivity.this, "没有找到该用户");
                    }
                } else {
                    ToastUtils.show(LoginActivity.this, e.toString());
                }
            }
        });

    }

    /**
     * 跳转到注册页面
     *
     * @param v
     */
    @OnClick(R.id.txt_toReg)
    public void onclick(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        Log.d("toreg", "test");
        startActivity(intent);
    }

}
