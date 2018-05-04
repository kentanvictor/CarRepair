package com.example.carrepair.adapter.categoryadapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.example.carrepair.utils.CartProvider;
import com.facebook.drawee.view.SimpleDraweeView;
import com.example.carrepair.R;
import com.example.carrepair.adapter.baseadapter.BaseViewHolder;
import com.example.carrepair.adapter.baseadapter.SimpleAdapter;
import com.example.carrepair.bean.hotbean.Wares;
import com.squareup.picasso.Picasso;

import java.util.List;



/**
 * 分类右部商品显示适配器
 */
public class WaresAdapter extends SimpleAdapter<Wares> {
    private CartProvider cartProvider;


    public WaresAdapter(Context context, List<Wares> datas) {
        super(context, R.layout.template_grid_wares, datas);
        cartProvider = CartProvider.getInstance(context);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, final Wares item) {
        viewHoder.getTextView(R.id.text_title_cate).setText(item.getName());
        viewHoder.getTextView(R.id.text_price_cate).setText("￥"+item.getPrice());
        SimpleDraweeView draweeView = (SimpleDraweeView) viewHoder.getView(R.id.drawee_view);
        //绑定url
        //draweeView.setImageURI(Uri.parse(item.getImgUrl()));
        Picasso.with(context).load(item.getImgUrl()).into(draweeView);

        viewHoder.getView(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CartFragment.mCategotyList.add(item);
                cartProvider.put(item);
                Toast.makeText(context, "添加购物车成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
