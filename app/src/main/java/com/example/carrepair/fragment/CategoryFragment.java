package com.example.carrepair.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.carrepair.bean.SliderBean;
import com.example.carrepair.widget.Contants;
import com.example.carrepair.R;
import com.example.carrepair.adapter.baseadapter.BaseAdapter;
import com.example.carrepair.adapter.categoryadapter.CategoryAdapter;
import com.example.carrepair.adapter.categoryadapter.WaresAdapter;
import com.example.carrepair.bean.Banner;
import com.example.carrepair.bean.Category;
import com.example.carrepair.bean.hotbean.Page;
import com.example.carrepair.bean.hotbean.Wares;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 分类列表
 */
public class CategoryFragment extends Fragment {
    String[] nameset = {"汽车保养美容服务", "汽车部件更换维修服务", "人工服务"};

    @ViewInject(R.id.recyclerview_category)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.recyclerview_wares)
    private RecyclerView mRecyclerviewWares;
    @ViewInject(R.id.refresh_layout_category)
    private MaterialRefreshLayout mRefreshLaout;
    @ViewInject(R.id.slider_category)
    private SliderLayout mSliderLayout;
    //左边导航适配器
    private CategoryAdapter mCategoryAdapter;
    //wares数据显示适配器
    private WaresAdapter mWaresAdatper;


    private int currPage = 1;
    private int totalPage = 1;
    private int pageSize = 10;
    private long category_id = 0;//左部导航栏的Id


    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFREH = 1;
    private static final int STATE_MORE = 2;

    private int state = STATE_NORMAL;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ViewUtils.inject(this, view);
        requestCategoryData();
        requestBannerData();
        initRefreshLayout();
        return view;
    }

    /**
     * wares数据刷新
     */
    private void initRefreshLayout() {
        mRefreshLaout.setLoadMore(true);
        mRefreshLaout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refreshData();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                if (currPage <= totalPage)
                    loadMoreData();
                else {
                    Log.d("test", "currPage and totalPage");
                    Toast.makeText(getContext(), "没有数据了...", Toast.LENGTH_SHORT).show();
                    mRefreshLaout.finishRefreshLoadMore();
                }
            }
        });
    }


    private void refreshData() {
        currPage = 1;
        state = STATE_REFREH;
        Log.d("refresh", "test");
        requestData(category_id);

    }

    private void loadMoreData() {
        currPage = ++currPage;
        state = STATE_MORE;
        requestData(category_id);

    }

    /**
     * 请求左部导航菜单数据
     */
    private void requestCategoryData() {
        //侧边选择栏
        Category[] categories = new Category[nameset.length];
        for (int i = 0; i < categories.length; i++) {
            categories[i] = new Category();
        }
        for (int i = 0; i < categories.length; i++) {
            categories[i].setName(nameset[i]);
            categories[i].setId(i);
        }
        List<Category> catest = new ArrayList<>();
        for (int i = 0; i < categories.length; i++) {
            catest.add(categories[i]);
        }
        showCategoryData(catest);

        if (catest != null && catest.size() > 0)
            category_id = catest.get(0).getId();
        requestData(category_id);

    }

    /**
     * 左部导航
     *
     * @param categories 导航列表
     */
    private void showCategoryData(List<Category> categories) {

        mCategoryAdapter = new CategoryAdapter(getContext(), categories);

        mCategoryAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //////////////////// getPosition //////////////////////
                //获取列表数据
                Category category = mCategoryAdapter.getItem(position);

                ///////////////////////////////////////////////////////
                refreshData();
                //获取列表数据id
                category_id = category.getId();

                Log.d("catetest", "test");
                requestData(category_id);


            }
        });

        mRecyclerView.setAdapter(mCategoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


    }


    /**
     * 请求轮播导航数据
     */
    private void requestBannerData() {
        //banner滑动框
        BmobQuery<Banner> query = new BmobQuery<Banner>();
        query.findObjects(new FindListener<Banner>() {
            @Override
            public void done(final List<Banner> list, BmobException e) {
                if (e == null) {
                   showSliderViews(list);
                } else {
                    Toast.makeText(getActivity(), "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                }
            }
        });
       /* List<Banner> bannerTest = new ArrayList<>();
        bannerTest.add(test);
        bannerTest.add(test2);
        showSliderViews(bannerTest);*/
    }

    /**
     * 显示轮播数据
     */
    private void showSliderViews(List<Banner> banners) {
        if (banners != null) {
            for (Banner banner : banners) {
                DefaultSliderView sliderView = new DefaultSliderView(this.getActivity());
                sliderView.image(banner.getImgUrl());
                sliderView.description(banner.getName());
                sliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(sliderView);
            }
            mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mSliderLayout.setCustomAnimation(new DescriptionAnimation());
            mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
            mSliderLayout.setDuration(3000);

        }
    }

    /**
     * 请求wares数据，并传入列表id
     *
     * @param categoryId 传入的点击的列表id显示该id对应商品
     */
//    private void requestWares(long categoryId) {
//        //商品选择框
//        Wares[] oilWares = new Wares[3];
//        Wares[] filterWares = new Wares[3];
//        //初始化
//        for (int i = 0; i < oilWares.length; i++) {
//            oilWares[i] = new Wares();
//            filterWares[i] = new Wares();
//        }
//        String[] oilUrl = new String[3];
//        String[] oilDes = new String[3];
//        float[] oilPrice = new float[3];
//        String[] filterUrl = new String[3];
//        String[] filterDes = new String[3];
//        float[] filterPrice = new float[3];
//        for (int i = 0; i < oilUrl.length; i++) {
//            oilUrl[i] = Contants.OIL.getOilUrl(i);
//            oilDes[i] = Contants.OIL.getOilDes(i);
//            oilPrice[i] = Contants.OIL.getOilPrice(i);
//            filterUrl[i] = Contants.FILTER.getFilterUrl(i);
//            filterDes[i] = Contants.FILTER.getFilterName(i);
//            filterPrice[i] = Contants.FILTER.getFilterPrice(i);
//        }
//        for (int i = 0; i < oilWares.length; i++) {
//            oilWares[i].setId((long) i);
//            oilWares[i].setImgUrl(oilUrl[i]);
//            oilWares[i].setName(oilDes[i]);
//            oilWares[i].setPrice(oilPrice[i]);
//            filterWares[i].setId((long) i);
//            filterWares[i].setImgUrl(filterUrl[i]);
//            filterWares[i].setName(filterDes[i]);
//            filterWares[i].setPrice(filterPrice[i]);
//        }
//        switch ((int) categoryId) {
//            case 0:
//                List<Wares> waresList = new ArrayList<>();
//                for (int i = 0; i < oilWares.length; i++) {
//                    waresList.add(oilWares[i]);
//                }
//                Log.d("switchtest", "test1");
//                Page<Wares> waresPage = new Page<>();
//                waresPage.setList(waresList);
//                showWaresData(waresList);
//                break;
//            case 1:
//                List<Wares> filterList = new ArrayList<>();
//                for (int i = 0; i < filterWares.length; i++) {
//                    filterList.add(filterWares[i]);
//                }
//                Log.d("switchtest", "test2");
//                Page<Wares> filterPage = new Page<>();
//                filterPage.setList(filterList);
//                showWaresData(filterList);
//                break;
//        }
//
//    }

    /**
     * 显示wares数据
     */

    private void showWaresData(List<Wares> wares) {
        switch (state) {
            case STATE_NORMAL:
                if (mWaresAdatper == null) {
                    mWaresAdatper = new WaresAdapter(getContext(), wares);
                    mRecyclerviewWares.setAdapter(mWaresAdatper);
                    mRecyclerviewWares.setLayoutManager(new GridLayoutManager(getContext(), 1));
                    mRecyclerviewWares.setItemAnimator(new DefaultItemAnimator());
                    //mRecyclerviewWares.addItemDecoration(new DividerGridItemDecoration(getContext()));
                } else {
                    mWaresAdatper.clearData();
                    mWaresAdatper.addData(wares);
                }
                break;
            case STATE_REFREH:
                mWaresAdatper.clearData();
                mWaresAdatper.addData(wares);
                mRecyclerviewWares.scrollToPosition(0);
                mRefreshLaout.finishRefresh();
                break;
            case STATE_MORE:
                mWaresAdatper.addData(mWaresAdatper.getDatas().size(), wares);
                mRecyclerviewWares.scrollToPosition(mWaresAdatper.getDatas().size());
                mRefreshLaout.finishRefreshLoadMore();
                break;
        }
    }

    /**
     * 请求wares数据，并传入列表id
     *
     * @param categoryId 传入的点击的列表id显示该id对应商品
     */
    private void requestData(long categoryId) {
        BmobQuery<Wares> query = new BmobQuery<Wares>();
        switch ((int) categoryId) {
            case 0:
                //查询oilWares里面数据
                query.addWhereEqualTo("description", "美容");
                query.setLimit(50);
                query.findObjects(new FindListener<Wares>() {
                    @Override
                    public void done(List<Wares> list, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getActivity(), "查询成功，共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                            //数据拉去得到之后的处理
                            Log.d("switchtest", "test1");
                            Page<Wares> waresPage = new Page<>();
                            waresPage.setList(list);
                            showWaresData(list);
                        } else {
                            Toast.makeText(getActivity(), "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case 1:
                query.addWhereEqualTo("description", "维修");
                query.setLimit(50);
                query.findObjects(new FindListener<Wares>() {
                    @Override
                    public void done(List<Wares> list, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getActivity(), "查询成功，共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                            //数据拉去得到之后的处理
                            Log.d("switchtest", "test1");
                            Page<Wares> waresPage = new Page<>();
                            waresPage.setList(list);
                            showWaresData(list);
                        } else {
                            Toast.makeText(getActivity(), "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case 2:
                query.addWhereEqualTo("description", "人工");
                query.setLimit(50);
                query.findObjects(new FindListener<Wares>() {
                    @Override
                    public void done(List<Wares> list, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getActivity(), "查询成功，共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                            //数据拉去得到之后的处理
                            Log.d("switchtest", "test1");
                            Page<Wares> waresPage = new Page<>();
                            waresPage.setList(list);
                            showWaresData(list);
                        } else {
                            Toast.makeText(getActivity(), "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            default:
                query.addWhereEqualTo("description", "美容");
                query.setLimit(50);
                query.findObjects(new FindListener<Wares>() {
                    @Override
                    public void done(List<Wares> list, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getActivity(), "查询成功，共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                            //数据拉去得到之后的处理
                            Log.d("switchtest", "test1");
                            Page<Wares> waresPage = new Page<>();
                            waresPage.setList(list);
                            showWaresData(list);
                        } else {
                            Toast.makeText(getActivity(), "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }

    }
}



