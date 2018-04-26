package com.example.carrepair.activity;

import android.content.Intent;
import android.view.View;

import com.example.carrepair.MainActivity;
import com.example.carrepair.MyApplication;
import com.example.carrepair.R;
import com.example.carrepair.bean.User;
import com.example.carrepair.utils.ToastUtils;
import com.example.carrepair.widget.ClearEditText;
import com.lidroid.xutils.view.annotation.ViewInject;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


public class UserManageActivity extends BaseActivity {
    @ViewInject(R.id.et_rename)
    private ClearEditText mName;
    @ViewInject(R.id.et_oldpwd)
    private ClearEditText mOldPwd;
    @ViewInject(R.id.et_newpwd)
    private ClearEditText mNewPwd;
    User user = new User();

    @Override
    public int getLayoutId() {
        return R.layout.activity_manager;
    }

    @Override
    public void init() {
        user = MyApplication.getInstance().getUser();
        if (user != null) {
            mName.setText(user.getUsername());
        }
    }

    @Override
    public void setToolbar() {
        getToolbar().setRightButtonText("重置");
        getToolbar().setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPwd = mOldPwd.getText().toString();
                String newPwd = mNewPwd.getText().toString();
                //判断旧密码是否匹配
               if (oldPwd.equals(user.getPwd())){
                   User newUser = new User(user.getId(), user.getEmail(), user.getLogo_url(), user.getUsername()
                           , newPwd);
                   //匹配成功，修改数据
                   newUser.update(user.getObjectId(), new UpdateListener() {
                       @Override
                       public void done(BmobException e) {
                           if(e==null){
                               ToastUtils.show(UserManageActivity.this,"更新成功,请重新登录");
                               MyApplication.getInstance().clearUser();//将本地保存的用户数据清空
                               Intent intent = new Intent(UserManageActivity.this,MainActivity.class);
                               startActivity(intent);
                           }
                       }
                   });
               }
               else {
                   ToastUtils.show(UserManageActivity.this,"你输入的旧密码有问题");
               }

            }
        });
    }
}
