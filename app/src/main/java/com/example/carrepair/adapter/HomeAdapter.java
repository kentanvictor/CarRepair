package com.example.carrepair.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carrepair.R;
import com.example.carrepair.bean.HomeBean;
import java.util.List;

/**
 * Created by 20304 on 2018/5/5.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<HomeBean> mTextDatas;
    private Context mContext;

    public HomeAdapter(List<HomeBean> datas, Context context) {
        mTextDatas = datas;
        this.mContext = context;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(mInflater.inflate(R.layout.template_home_cardview3
                , parent, false));
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.textTitle.setText(mTextDatas.get(position).getHomeTitle());
        holder.textDes.setText(mTextDatas.get(position).getHomeDes());
    }

    @Override
    public int getItemCount() {
        return mTextDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textTitle;
        TextView textDes;


        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.home_title);
            textDes = itemView.findViewById(R.id.textview_home);
        }
        @Override
        public void onClick(View view) {
        }
    }

}

