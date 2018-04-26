package com.example.carrepair.adapter.categoryadapter;

import android.content.Context;

import com.example.carrepair.R;
import com.example.carrepair.adapter.baseadapter.BaseViewHolder;
import com.example.carrepair.adapter.baseadapter.SimpleAdapter;
import com.example.carrepair.bean.Category;

import java.util.List;

/**
 * 分类左部种类选项适配器
 */

public class CategoryAdapter extends SimpleAdapter<Category> {

    public CategoryAdapter(Context context, List<Category> datas) {
        super(context, R.layout.template_single_text, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, Category item) {

        viewHoder.getTextView(R.id.textView_category).setText(item.getName());
    }
}
