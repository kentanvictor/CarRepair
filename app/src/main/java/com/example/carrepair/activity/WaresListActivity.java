package com.example.carrepair.activity;

/*
 * Created by JohnnyTan on 2018/3/21.
 */

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

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

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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
        BmobQuery<Wares> query = new BmobQuery<Wares>();
        switch (categoryId){
            case 0:
                query.addWhereEqualTo("description", "美容");
                query.setLimit(50);
                query.findObjects(new FindListener<Wares>() {
                    @Override
                    public void done(List<Wares> list, BmobException e) {
                        if (e == null) {
                            Toast.makeText(WaresListActivity.this, "查询成功，共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                            //数据拉去得到之后的处理
                            Log.d("switchtest", "test1");
                            Page<Wares> waresPage = new Page<>();
                            waresPage.setList(list);
                            showWaresData(list);
                        } else {
                            Toast.makeText(WaresListActivity.this, "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(WaresListActivity.this, "查询成功，共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                            //数据拉去得到之后的处理
                            Log.d("switchtest", "test1");
                            Page<Wares> waresPage = new Page<>();
                            waresPage.setList(list);
                            showWaresData(list);
                        } else {
                            Toast.makeText(WaresListActivity.this, "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(WaresListActivity.this, "查询成功，共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                            //数据拉去得到之后的处理
                            Log.d("switchtest", "test1");
                            Page<Wares> waresPage = new Page<>();
                            waresPage.setList(list);
                            showWaresData(list);
                        } else {
                            Toast.makeText(WaresListActivity.this, "失败：" + e.getMessage() + "," + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }

    }

    /**
     * 显示wares数据
     */

    private void showWaresData(List<Wares> wares) {
        switch (state) {
            case STATE_NORMAL:
                if (mWaresAdatper == null) {
                    mWaresAdatper = new WaresAdapter(WaresListActivity.this, wares);
                    mRecycleViewWares.setAdapter(mWaresAdatper);
                    mRecycleViewWares.setLayoutManager(new GridLayoutManager(WaresListActivity.this, 1));
                    mRecycleViewWares.setItemAnimator(new DefaultItemAnimator());
                    //mRecyclerviewWares.addItemDecoration(new DividerGridItemDecoration(getContext()));
                } else {
                    mWaresAdatper.clearData();
                    mWaresAdatper.addData(wares);
                }
                break;
            case STATE_REFREH:
                mWaresAdatper.clearData();
                mWaresAdatper.addData(wares);
                mRecycleViewWares.scrollToPosition(0);
                mRefreshLaout.finishRefresh();
                break;
            case STATE_MORE:
                mWaresAdatper.addData(mWaresAdatper.getDatas().size(), wares);
                mRecycleViewWares.scrollToPosition(mWaresAdatper.getDatas().size());
                mRefreshLaout.finishRefreshLoadMore();
                break;
        }
    }
}
