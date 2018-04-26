package com.example.carrepair.activity;

/*
 * Created by JohnnyTan on 2018/3/21.
 */

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.carrepair.widget.Contants;
import com.example.carrepair.R;
import com.example.carrepair.adapter.categoryadapter.WaresAdapter;
import com.example.carrepair.bean.hotbean.Page;
import com.example.carrepair.bean.hotbean.Wares;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class WaresListActivity extends BaseActivity{
    public static int waresList = 999;
    private WaresAdapter mWaresAdatper;

    private int currPage = 1;
    private int totalPage = 1;
    private int pageSize = 10;

    private static final int STATE_NORMAL = 0;
    private static final int STATE_REFREH = 1;
    private static final int STATE_MORE = 2;
    private int state = STATE_NORMAL;




    @ViewInject(R.id.recycle_view)
    private RecyclerView mRecycleViewWares;
    @ViewInject(R.id.refresh_layout_category)
    private MaterialRefreshLayout mRefreshLaout;
    @Override
    public int getLayoutId() {
        return R.layout.activity_wares_list;
    }

    @Override
    public void init() {
        initRefreshLayout();
    }

    @Override
    public void setToolbar() {
    }

    private void initRefreshLayout() {
        requestWares(waresList);
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
                    Log.d("test","currPage and totalPage");
                    mRefreshLaout.finishRefreshLoadMore();
                }
            }
        });
    }
    /**
     * 刷新数据
     */
    private void refreshData() {

        currPage = 1;

        state = STATE_REFREH;
        Log.d("refresh","test");
        requestWares(waresList);

    }
    /**
     * 加载更多
     */
    private void loadMoreData() {

        currPage = ++currPage;
        state = STATE_MORE;
        requestWares(waresList);

    }


    private void requestWares(int categoryId) {
        //商品选择框
        Wares[] oilWares = new Wares[3];
        Wares[] filterWares = new Wares[3];
        //初始化
        for (int i = 0; i < oilWares.length;i++)
        {
            oilWares[i] = new Wares();
            filterWares[i] = new Wares();
        }
        String[] oilUrl = new String[3];
        String[] oilDes = new String[3];
        float[] oilPrice = new float[3];
        String[] filterUrl = new String[3];
        String[] filterDes = new String[3];
        float[] filterPrice = new float[3];
        for (int i = 0; i < oilUrl.length; i++) {
            oilUrl[i] = Contants.OIL.getOilUrl(i);
            oilDes[i] = Contants.OIL.getOilDes(i);
            oilPrice[i] = Contants.OIL.getOilPrice(i);
            filterUrl[i] = Contants.FILTER.getFilterUrl(i);
            filterDes[i] = Contants.FILTER.getFilterName(i);
            filterPrice[i] = Contants.FILTER.getFilterPrice(i);
        }
        for (int i = 0; i < oilWares.length; i++) {
            oilWares[i].setId((long)i);
            oilWares[i].setImgUrl(oilUrl[i]);
            oilWares[i].setName(oilDes[i]);
            oilWares[i].setPrice(oilPrice[i]);
            filterWares[i].setId((long) i);
            filterWares[i].setImgUrl(filterUrl[i]);
            filterWares[i].setName(filterDes[i]);
            filterWares[i].setPrice(filterPrice[i]);
        }
        switch (categoryId){
            case 0:
                List<Wares> waresList = new ArrayList<>();
                for (int i = 0; i < oilWares.length;i++){
                    waresList.add(oilWares[i]);
                }
                Log.d("switchtest","test1");
                Page<Wares> waresPage = new Page<>();
                waresPage.setList(waresList);
                mWaresAdatper = new WaresAdapter(getBaseContext(),waresList);
                mRecycleViewWares.setAdapter(mWaresAdatper);
                mRecycleViewWares.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
                mRecycleViewWares.setItemAnimator(new DefaultItemAnimator());
                break;
            case 1:
                List<Wares> filterList = new ArrayList<>();
                for (int i = 0; i < filterWares.length;i++){
                    filterList.add(filterWares[i]);
                }
                Log.d("switchtest","test2");
                Page<Wares> filterPage = new Page<>();
                filterPage.setList(filterList);
                mWaresAdatper = new WaresAdapter(getBaseContext(),filterList);
                mRecycleViewWares.setAdapter(mWaresAdatper);
                mRecycleViewWares.setLayoutManager(new GridLayoutManager(getBaseContext(), 1));
                mRecycleViewWares.setItemAnimator(new DefaultItemAnimator());
                break;
        }

    }
}
