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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mSliderLayout = view.findViewById(R.id.slider);

        indicator = view.findViewById(R.id.custom_indicator);
        requestImages();
        initSlider();
        initRecyclerView(view);
        return view;
    }

    //请求轮播Banner图片数据
    private void requestImages() {
        initSlider();
    }

    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        List<HomeCampaign> campaignList = new ArrayList<>();
        Campaign[] campaign = new Campaign[6];
        for (int i = 0; i < campaign.length; i++) {
            campaign[i] = new Campaign();
        }
        for (int i = 0; i < campaign.length; i++) {
            campaign[i].setImgUrl(Contants.NOTICE.getDay(i));
            campaign[i].setTitle(Contants.NOTICE.getTitle(i));
        }
        HomeCampaign homeCampaign = new HomeCampaign();
        HomeCampaign homeCampaign1 = new HomeCampaign();
        homeCampaign.setTitle("每日特卖");
        homeCampaign.setCpOne(campaign[0]);
        homeCampaign.setCpTwo(campaign[1]);
        homeCampaign.setCpThree(campaign[2]);
        homeCampaign1.setTitle("今日特价");
        homeCampaign1.setCpOne(campaign[3]);
        homeCampaign1.setCpTwo(campaign[4]);
        homeCampaign1.setCpThree(campaign[5]);
        campaignList.add(homeCampaign);
        campaignList.add(homeCampaign1);
        mAdapter = new HomeCategoryAdapter(campaignList, getContext());
        mRecyclerView.setAdapter(mAdapter);
        initData(campaignList);
        //设置边距
        mRecyclerView.addItemDecoration(new DividerItemDecortion());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

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
        TextSliderView textSliderView = new TextSliderView(this.getActivity());
        textSliderView.image(Contants.SLIDER.getUrl(0));
        Picasso.with(getContext()).load(textSliderView.getUrl())
                .into((ImageView) textSliderView.getView().findViewById(R.id.daimajia_slider_image));
        textSliderView.description("固特异品牌日");
        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "固特异品牌日", Toast.LENGTH_SHORT).show();
            }
        });

        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
        textSliderView2.image(Contants.SLIDER.getUrl(1));
        Picasso.with(getContext()).load(textSliderView2.getUrl())
                .into((ImageView) textSliderView.getView().findViewById(R.id.daimajia_slider_image));
        textSliderView2.description("推轮捧新");
        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "推轮捧新", Toast.LENGTH_SHORT).show();
            }
        });

        TextSliderView textSliderView3 = new TextSliderView(this.getActivity());
        textSliderView3.image(Contants.SLIDER.getUrl(2));
        Picasso.with(getContext()).load(textSliderView3.getUrl())
                .into((ImageView) textSliderView.getView().findViewById(R.id.daimajia_slider_image));
        textSliderView3.description("胎压检测");
        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "胎压检测", Toast.LENGTH_SHORT).show();
            }
        });

        TextSliderView textSliderView4 = new TextSliderView(this.getActivity());
        textSliderView4.image(Contants.SLIDER.getUrl(3));
        Picasso.with(getContext()).load(textSliderView4.getUrl())
                .into((ImageView) textSliderView.getView().findViewById(R.id.daimajia_slider_image));
        textSliderView4.description("远足野营，其乐无穷");
        textSliderView4.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "远足野营，其乐无穷", Toast.LENGTH_SHORT).show();
            }
        });

        mSliderLayout.addSlider(textSliderView);
        mSliderLayout.addSlider(textSliderView2);
        mSliderLayout.addSlider(textSliderView3);
        mSliderLayout.addSlider(textSliderView4);
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
