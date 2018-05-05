package com.example.carrepair.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.carrepair.adapter.HomeAdapter;
import com.example.carrepair.bean.HomeBean;
import com.example.carrepair.bean.SliderBean;
import com.example.carrepair.bean.hotbean.Page;
import com.example.carrepair.bean.hotbean.Wares;
import com.example.carrepair.widget.Contants;
import com.google.gson.Gson;
import com.example.carrepair.R;
import com.example.carrepair.adapter.DividerItemDecortion;
import com.example.carrepair.adapter.HomeCategoryAdapter;
import com.example.carrepair.bean.Banner;
import com.example.carrepair.bean.recyclerbean.Campaign;
import com.example.carrepair.bean.recyclerbean.HomeCampaign;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.content.ContentValues.TAG;


/**
 * 主页
 * AndroidImageSlider 轮播广告的实现：SliderLayout
 * RecyclerView 商品分类展示：
 */
public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SliderLayout mSliderLayout;
    private PagerIndicator indicator;
    private HomeCategoryAdapter mAdapter;
    private Gson mGson = new Gson();
    private List<Banner> mBanner;
    private Context mContext;
    private int i = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mSliderLayout = view.findViewById(R.id.slider);

        indicator = view.findViewById(R.id.custom_indicator);
        requestImages();
        initSlider();
        initRecyclerView(view);
        //initmRecyclerView(view);
        return view;
    }

    //请求轮播Banner图片数据
    private void requestImages() {
        initSlider();
    }
    //初始化滑动推送数据
    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        BmobQuery<HomeCampaign> query = new BmobQuery<HomeCampaign>();
        query.addWhereEqualTo("name", "推送");
        query.setLimit(50);
        query.findObjects(new FindListener<HomeCampaign>() {
            @Override
            public void done(List<HomeCampaign> list, BmobException e) {
                if (e == null) {
                    Toast.makeText(getActivity(), "查询成功，共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                    //数据拉去得到之后的处理
                    Log.d("BmobTest", "test1");
                    mAdapter = new HomeCategoryAdapter(list, getContext());
                    mRecyclerView.setAdapter(mAdapter);
                    initData(list);
                } else {
                    Toast.makeText(getActivity(), "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置边距
        mRecyclerView.addItemDecoration(new DividerItemDecortion());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }
    //初始化滑动推送数据
   /* private void initmRecyclerView(View view){
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        BmobQuery<HomeBean> query = new BmobQuery<HomeBean>();
        query.findObjects(new FindListener<HomeBean>() {
            @Override
            public void done(List<HomeBean> list, BmobException e) {
              if (e == null){
                  Toast.makeText(getActivity(), "查询成功，共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                  //数据拉去得到之后的处理
                  Log.d("BmobTest", "test1");
                  mHomeAdapter = new HomeAdapter(list,getContext());
                  mRecyclerView.setAdapter(mHomeAdapter);
              }else{
                  Toast.makeText(getActivity(), "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
              }
            }
        });
        //设置边距
        mRecyclerView.addItemDecoration(new DividerItemDecortion());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }*/

    //获取主页商品数据
    private void initData(List<HomeCampaign> homeCampaigns) {
        mAdapter = new HomeCategoryAdapter(homeCampaigns, getActivity());
        mAdapter.setOnCampaignClickListenner(new HomeCategoryAdapter.OnCampaignClickListenner() {
            @Override
            public void onClick(View view, Campaign campaign) {
                Toast.makeText(getContext(), "title=" + campaign.getTitle(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        //设置边距
        mRecyclerView.addItemDecoration(new DividerItemDecortion());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    //初始化slider
    private void initSlider() {
        BmobQuery<SliderBean> query = new BmobQuery<SliderBean>();
        query.findObjects(new FindListener<SliderBean>() {
            @Override
            public void done(final List<SliderBean> list, BmobException e) {
                if (e == null) {
                    final TextSliderView[] textSliderView = new TextSliderView[list.size()];
                    for (int j = 0; j < list.size();j++){
                        textSliderView[j] =new TextSliderView(getActivity());
                    }
                    for (int i = 0; i < textSliderView.length;i++){
                        textSliderView[i].image(list.get(i).getImageUrl());
                        Picasso.with(getContext()).load(textSliderView[i].getUrl())
                                .into((ImageView) textSliderView[i].getView().findViewById(R.id.daimajia_slider_image));
                        textSliderView[i].description(list.get(i).getImageText());
                        final int temp = i ;
                        textSliderView[i].setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                Toast.makeText(HomeFragment.this.getActivity(),list.get(temp).getImageText(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        mSliderLayout.addSlider(textSliderView[i]);
                    }
                }else {
                    Toast.makeText(getActivity(), "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置指示器
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //自定义指示器
        mSliderLayout.setCustomIndicator(indicator);
        //设置动画效果
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        //设置转场效果
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        //设置时长
        mSliderLayout.setDuration(3000);
        // 点击事件监听
        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled: hahahahahahahah");
            }
            @Override
            public void onPageSelected(int position) {

                Log.d(TAG, "onPageSelected: hahahahahahhahh");
            }
            @Override
            public void onPageScrollStateChanged(int state) {

                Log.d(TAG, "onPageScrollStateChanged: hahahhaha");
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        mSliderLayout.stopAutoCycle();
    }
}
