package com.johnnytan.dell.nothelloworld.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.johnnytan.dell.nothelloworld.R;
import com.johnnytan.dell.nothelloworld.view.fragment.HomeFragment;
import com.johnnytan.dell.nothelloworld.view.fragment.SearchFragment;
import com.johnnytan.dell.nothelloworld.view.fragment.ShoppingFragment;
import com.johnnytan.dell.nothelloworld.view.fragment.UserFragment;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNV;
    private android.support.v4.app.FragmentManager fm;
    private Fragment mFragment;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "a93dee828562ff3c2fa71098e3af2ed8");
        init();
    }

    private void init() {
        bottomNV = findViewById(R.id.bottomNV);
        bottomNV.setOnNavigationItemSelectedListener(this);
        //使用fragmentManager管理fragment视图
        fm = getSupportFragmentManager();
        mFragment = fm.findFragmentById(getContainerId());
        if (mFragment == null) {
            mFragment = createFragment();
            fm.beginTransaction()
                    .add(getContainerId(), mFragment)
                    .commit();
        }
    }
    //bottmnavigaionview点击事件
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                switchFragment(HomeFragment.newInstance());
                break;
            case R.id.menu_search:
                switchFragment(SearchFragment.newInstance());
                break;
            case R.id.menu_shopping:
                switchFragment(ShoppingFragment.newInstance());
                break;
            case R.id.menu_user:
                switchFragment(UserFragment.newInstance());
                break;

        }
        return true;
    }

    //工具方法
    protected Fragment createFragment() {
        return HomeFragment.newInstance();
    }
    //fragment选择
    public void switchFragment(Fragment fragment) {
        if (mFragment == null
                || !fragment.getClass().getName().equals(mFragment.getClass().getName())) {
            fm.beginTransaction()
                    .replace(getContainerId(), fragment)
                    .commit();
            mFragment = fragment;
        }
    }
    //得到容器id
    public int getContainerId() {
        return R.id.lay_container;
    }

    public void toast(String printInfo) {
        Toast.makeText(this, printInfo, Toast.LENGTH_SHORT).show();
    }
}
