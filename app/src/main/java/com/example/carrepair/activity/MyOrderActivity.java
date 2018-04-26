package com.example.carrepair.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.carrepair.MyApplication;
import com.example.carrepair.R;
import com.example.carrepair.adapter.cartadapter.CartAdapter;
import com.example.carrepair.bean.cartbean.ShoppingCart;
import com.example.carrepair.utils.CartProvider;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的订单
 */

public class MyOrderActivity extends AppCompatActivity {
    public static final int STATUS_ALL = 1000;
    private int status = STATUS_ALL;
    @ViewInject(R.id.recycle_view)
    private RecyclerView mRecycleView;
    private CartProvider cartProvider;
    private CartAdapter mCartAdapter;
    List<ShoppingCart> order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

    }
    /**
     * 获取订单数据
     */
    private void getOrders() {
        String userId = MyApplication.getInstance().getUser().getId() + "";

        if (!TextUtils.isEmpty(userId)) {
            Map<String, String> params = new HashMap<>();

            params.put("user_id", userId);
            params.put("status", status + "");

        }
    }
    /**
     * 显示订单数据
     * @param orders
     */
    private void showOrders(List<ShoppingCart> orders) {
    }

}
