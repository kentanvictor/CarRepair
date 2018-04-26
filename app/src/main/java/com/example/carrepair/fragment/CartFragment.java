package com.example.carrepair.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrepair.MainActivity;
import com.example.carrepair.MyApplication;
import com.example.carrepair.R;
import com.example.carrepair.adapter.cartadapter.CartAdapter;
import com.example.carrepair.bean.cartbean.ShoppingCart;
import com.example.carrepair.bean.hotbean.Wares;
import com.example.carrepair.utils.CartProvider;
import com.example.carrepair.widget.CnToolbar;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 * 添加商品到购物车，CartProvider获取购物车数据，并显示总价，选中的商品可进行购买可以跳到结算页面
 * 购物车为空则不能购买
 */
public class CartFragment extends BaseFragment implements View.OnClickListener {
    public static final int ACTION_EDIT = 1;
    public static final int ACTION_CAMPLATE = 2;

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.checkbox_all)
    private CheckBox mCheckBox;
    @ViewInject(R.id.txt_total)
    private TextView mTextTotal;
    @ViewInject(R.id.btn_order)
    private Button mBtnOrder;
    @ViewInject(R.id.btn_del)
    private Button mBtnDel;


    @ViewInject(R.id.toolbar)
    protected CnToolbar mToolbar;


    private CartAdapter mAdapter;
    private CartProvider cartProvider;

    public static List<Wares> mCategotyList = new ArrayList<>();

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View cartView = inflater.inflate(R.layout.fragment_cart, container, false);
        ViewUtils.inject(this, cartView);
        init();
        return cartView;

    }

    @Override
    public void init() {

        cartProvider = CartProvider.getInstance(getContext());

        changeToolbar();
        showData();


    }


    @OnClick(R.id.btn_del)
    public void delCart(View view) {

        mAdapter.delCart();
    }

    /**
     * 显示购物车数据
     */
    private void showData() {
        List<ShoppingCart> carts = cartProvider.getAll();
        mAdapter = new CartAdapter(getContext(), carts, mCheckBox, mTextTotal);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }
    /**
     * 刷新数据
     */
    public void refData() {
        mAdapter.clearData();
        List<ShoppingCart> carts = cartProvider.getAll();
        mAdapter.addData(carts);
        //点击更改价格
        mAdapter.showTotalPrice();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            MainActivity activity = (MainActivity) context;
            mToolbar =activity.findViewById(R.id.toolbar);
            changeToolbar();
        }
    }
    /**
     * 隐藏删除按钮
     */
    public void changeToolbar() {
        mToolbar.hideSearchView();
        mToolbar.showTitleView();
        mToolbar.setTitle(R.string.cart);
        mToolbar.setRightButtonText("编辑");
        mToolbar.getRightButton().setVisibility(View.VISIBLE);
        mToolbar.getRightButton().setOnClickListener(this);
        mToolbar.getRightButton().setTag(ACTION_EDIT);
    }
    /**
     * 显示删除按钮
     */
    private void showDelControl() {
        mToolbar.getRightButton().setText("完成");
        mTextTotal.setVisibility(View.GONE);
        mBtnOrder.setVisibility(View.GONE);
        mBtnDel.setVisibility(View.VISIBLE);
        //设置为完成
        mToolbar.getRightButton().setTag(ACTION_CAMPLATE);
        mAdapter.checkAll_None(false);
        mCheckBox.setChecked(false);

    }

    private void hideDelControl() {

        mTextTotal.setVisibility(View.VISIBLE);
        mBtnOrder.setVisibility(View.VISIBLE);


        mBtnDel.setVisibility(View.GONE);
        mToolbar.setRightButtonText("编辑");
        mToolbar.getRightButton().setTag(ACTION_EDIT);

        mAdapter.checkAll_None(true);
        mAdapter.showTotalPrice();

        mCheckBox.setChecked(true);
    }


    @Override
    public void onClick(View v) {

        int action = (int) v.getTag();
        if (ACTION_EDIT == action) {

            showDelControl();
        } else if (ACTION_CAMPLATE == action) {

            hideDelControl();
        }

    }
    /**
     * 结算按钮点击事件
     * @param view
     */
    @OnClick(R.id.btn_order)
    //付款
    public void toOrder(View view) {
        if (MyApplication.getInstance().getUser() == null){
            Toast.makeText(getActivity(),"请先注册",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "可以结账", Toast.LENGTH_SHORT).show();
        }
    }
}
