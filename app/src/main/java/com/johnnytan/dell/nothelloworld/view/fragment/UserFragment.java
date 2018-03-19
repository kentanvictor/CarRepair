package com.johnnytan.dell.nothelloworld.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.johnnytan.dell.nothelloworld.utils.EidtTextClearTools;
import com.johnnytan.dell.nothelloworld.R;
import com.johnnytan.dell.nothelloworld.bean.User;
import com.johnnytan.dell.nothelloworld.view.activity.SignUpActivity;

/*
 * Created by JohnnyTan on 2018/3/17.
 */

public class UserFragment extends Fragment implements View.OnClickListener{
    private EditText mUserName;
    private EditText mPassWord;
    private ImageView mNameClear;
    private ImageView mPwdClear;
    private Button mPush;
    private TextView signUp;
    private EidtTextClearTools clearTools;
    private String userName;
    private String userPassWord;
    private User userData;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View userView = inflater.inflate(R.layout.fragment_user, container, false);
        init(userView);
        return userView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.link_signup:
                Intent intent = new Intent(getActivity(),SignUpActivity.class);
                startActivity(intent);

        }
    }

    private void init(View view) {
        mUserName = view.findViewById(R.id.et_userName);
        mPassWord = view.findViewById(R.id.et_password);
        mNameClear = view.findViewById(R.id.iv_nameClear);
        mPwdClear = view.findViewById(R.id.iv_pwdClear);
        signUp = view.findViewById(R.id.link_signup);
        clearTools = new EidtTextClearTools();
        clearTools.addClearListener(mUserName, mNameClear);
        clearTools.addClearListener(mPassWord, mPwdClear);
        signUp.setText(Html.fromHtml(getString(R.string.sign_up)));
        signUp.setOnClickListener(this);
    }


    public void toast(Context context, String printInfo) {
        Toast.makeText(context, printInfo, Toast.LENGTH_SHORT).show();
    }
}
