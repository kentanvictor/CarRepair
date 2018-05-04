package com.example.carrepair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.example.carrepair.bean.Banner;
import com.example.carrepair.bean.SliderBean;
import com.example.carrepair.bean.Tab;
import com.example.carrepair.bean.hotbean.Wares;
import com.example.carrepair.bean.recyclerbean.Campaign;
import com.example.carrepair.bean.recyclerbean.HomeCampaign;
import com.example.carrepair.fragment.CartFragment;
import com.example.carrepair.fragment.CategoryFragment;
import com.example.carrepair.fragment.HomeFragment;
import com.example.carrepair.fragment.MineFragment;
import com.example.carrepair.widget.CnToolbar;
import com.example.carrepair.widget.Contants;
import com.example.carrepair.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;

/**
 * MainActivity
 */
public class MainActivity extends AppCompatActivity {

    CartFragment cartFragment;

    private LayoutInflater mInflater;

    private FragmentTabHost mTabhost;

    private CnToolbar mToolbar;

    public static String BMOBAPPID = "7d90889fd39840bdd82841531f4c8513";

    private List<Tab> mTabs = new ArrayList<>(5);
    private static String TAG = "Bmob";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initToolBar();
        initTab();
    }

    private void init() {
        Bmob.initialize(this, BMOBAPPID);
        //初始化数据
       /* final String imgeUrl = "https://img1.tuhu.org/Home/Image/2E3206E5A43D7B8CB089596240C8D9A2.jpg";
        final String imgeUrl2 = "https://img1.tuhu.org/Home/Image/579F320072C62962BE2C3274920D7939.png";
        final String nameTest1 = "Hello";
        final String nameTest2 = "World";
        Banner test = new Banner();
        Banner test2 = new Banner();
        test.setImgUrl(imgeUrl);
        test.setName(nameTest1);
        test.setDescription("轮播");
        test2.setImgUrl(imgeUrl2);
        test2.setName(nameTest2);
        test2.setDescription("轮播");
        List<BmobObject> BannerList = new ArrayList<>();
        BannerList.add(test);
        BannerList.add(test2);
        new BmobBatch().insertBatch(BannerList).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> list, BmobException e) {
                for(int i=0;i<list.size();i++){
                    BatchResult result = list.get(i);
                    BmobException ex =result.getError();
                    if(ex==null){
                        Log.d(TAG,"第" + i + "个数据批量添加成功：" + result.getCreatedAt() + "," + result.getObjectId() + "," + result.getUpdatedAt());
                    }else{
                        Log.d(TAG,"第"+i+"个数据批量添加失败："+ex.getMessage()+","+ex.getErrorCode());
                    }
                }
            }
        });*/
      /* SliderBean[] sliderbean = new SliderBean[4];
        for (int i = 0; i < sliderbean.length; i++) {
            sliderbean[i] = new SliderBean();
        }
        for (int i = 0; i < sliderbean.length; i++) {
            sliderbean[i].setImageUrl(Contants.SLIDER.getUrl(i));
            sliderbean[i].setImageText(Contants.SLIDER.getText(i));
        }
        List<BmobObject> SliderViewList = new ArrayList<>();
            for (int i= 0;i < sliderbean.length;i++){
                SliderViewList.add(sliderbean[i]);
            }
        //上传数据到服务器
        new BmobBatch().insertBatch(SliderViewList).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> list, BmobException e) {
                if(e==null){
                    for(int i=0;i<list.size();i++){
                        BatchResult result = list.get(i);
                        BmobException ex =result.getError();
                        if(ex==null){
                            Log.d(TAG,"第" + i + "个数据批量添加成功：" + result.getCreatedAt() + "," + result.getObjectId() + "," + result.getUpdatedAt());
                        }else{
                            Log.d(TAG,"第"+i+"个数据批量添加失败："+ex.getMessage()+","+ex.getErrorCode());
                        }
                    }
                }else{
                Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
            }
            }
        });*/
    }

    private void initToolBar() {
        mToolbar = findViewById(R.id.toolbar);

    }
    /**
     * 初始化tab数据
     * FragmentTabHost基本使用
     * 1. Activity要继承FragmentActivity
     * 2.调⽤setup()⽅法
     * 3.添加TabSpec(indicator)
     */
    private void initTab() {
        /**
         * 添加tab显示的文字和图片，绑定fragment
         */
        Tab tab_home = new Tab(HomeFragment.class, R.string.home, R.drawable.selector_icon_home);
        Tab tab_category = new Tab(CategoryFragment.class, R.string.catagory, R.drawable.selector_icon_category);
        Tab tab_cart = new Tab(CartFragment.class, R.string.cart, R.drawable.selector_icon_cart);
        Tab tab_mine = new Tab(MineFragment.class, R.string.mine, R.drawable.selector_icon_mine);

        mTabs.add(tab_home);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);

        mInflater = LayoutInflater.from(this);
        mTabhost = this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs) {
            //实例化TabSpec对象
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            //设置indicator
            tabSpec.setIndicator(buildIndicator(tab));
            //添加tabSpec
            mTabhost.addTab(tabSpec, tab.getFragment(), null);

        }
        //刷新数据
        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (s == getString(R.string.cart)) {
                    mToolbar.hideSearchView();
                    mToolbar.showTitleView();
                    mToolbar.getRightButton().setVisibility(View.VISIBLE);
                    refData();

                } else if (s == getString(R.string.mine)) {
                    mToolbar.hideSearchView();
                    mToolbar.hideTitleView();
                    mToolbar.getRightButton().setVisibility(View.GONE);
                } else {
                    mToolbar.showSearchView();
                    mToolbar.hideTitleView();
                    mToolbar.getRightButton().setVisibility(View.GONE);
                }
            }
        });

        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);


    }

    /**
     * 刷新购物车数据
     */
    private void refData() {
        if (cartFragment == null) {
            //当fragment只有在点击之后，才会添加进来
            android.support.v4.app.Fragment fragment = getSupportFragmentManager()
                    .findFragmentByTag(getString(R.string.cart));
            //判断当前fragment是否被点击，点击才存在
            if (fragment != null) {

                CartFragment cartFragment = (CartFragment) fragment;
                // 第一次刷新初始化
                cartFragment.refData();
            }

        } else {
            cartFragment.refData();
        }
    }

    /**
     * indicator包括ImageView和TextView
     * @param tab
     * @return
     */
    private View buildIndicator(Tab tab) {


        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = view.findViewById(R.id.icon_tab);
        TextView text = view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());

        return view;
    }
}
