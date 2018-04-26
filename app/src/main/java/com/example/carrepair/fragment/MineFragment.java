package com.example.carrepair.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carrepair.activity.MyOrderActivity;
import com.example.carrepair.activity.UserManageActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.example.carrepair.MyApplication;
import com.example.carrepair.widget.Contants;
import com.example.carrepair.activity.LoginActivity;
import com.example.carrepair.R;
import com.example.carrepair.bean.User;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 我的
 */
public class MineFragment extends BaseFragment{
    @ViewInject(R.id.img_head)
    private CircleImageView mImgHead;
    @ViewInject(R.id.txt_username)
    private TextView mTxUserName;


    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mineView = inflater.inflate(R.layout.fragment_mine,container,false);
        ViewUtils.inject(this,mineView);
        init();
        return  mineView;
    }

    /**
     * 刚进入我的页面就要初始化用户数据
     */
    @Override
    public void init() {
        User user = MyApplication.getInstance().getUser();
        showUser(user);
    }
    /**
     * 登录点击事件
     * @param view
     */
    @OnClick(value = {R.id.img_head,R.id.txt_username})
    private void toLogin(View view) {
        if (MyApplication.getInstance().getUser() == null){
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent, Contants.REQUEST_CODE);
        }
        else{
            mTxUserName.setText("您已登录");
        }

    }

    //拿到用户信息后回掉到mineFragment
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        User user = MyApplication.getInstance().getUser();

        showUser(user);
    }


    /**
     * 显示用户数据
     * @param user
     */
    private void showUser(User user) {
        if (user != null) {
            mTxUserName.setText(user.getUsername());
            //Picasso.with(getActivity()).load(R.id.img_head).into(mImgHead);
        } else {
            mTxUserName.setText(R.string.to_login);
        }
    }
    // 退出登录
    @OnClick(R.id.btn_logout)
    public void logOut(View view ) {
        MyApplication.getInstance().clearUser();
        showUser(null);
    }

    /**
     * 我的订单显示。需先判断是否已经登录
     * @param view
     */
    @OnClick(R.id.tv_my_order)
    public void showMyOrder(View view){
        Intent intent = new Intent(getActivity(), MyOrderActivity.class);
        startActivity(intent, true);
    }

    /**
     * 用户管理界面显示，需先判断是否已经登录
     * @param view
     */
    @OnClick(R.id.tv_user)
    public void manageUser(View view){
        Intent intent = new Intent(getActivity(),UserManageActivity.class);
        startActivity(intent);
    }
}
