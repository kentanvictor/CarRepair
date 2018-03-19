package com.johnnytan.dell.nothelloworld.adapter;

/*
 * Created by JohnnyTan on 2018/3/18.
 */

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.johnnytan.dell.nothelloworld.bean.homebean.HomeItem;
import com.johnnytan.dell.nothelloworld.R;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<HomeItem,BaseViewHolder>{

    public MyAdapter(int layoutResId, @Nullable List<HomeItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.card_text, item.getTitle());
        helper.setImageResource(R.id.card_image, item.getImageResource());
    }
}
