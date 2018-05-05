package com.example.carrepair.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carrepair.bean.HomeBean;
import com.squareup.picasso.Picasso;
import com.example.carrepair.R;
import com.example.carrepair.bean.HomeCategory;
import com.example.carrepair.bean.recyclerbean.Campaign;
import com.example.carrepair.bean.recyclerbean.HomeCampaign;

import java.util.List;

/**
 * 主页商品适配器
 */

public class HomeCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int VIEW_TYPE_L = 0;
    private static int VIEW_TYPE_R = 1;
    private static int VIEW_TYPE_T = 2;
    private LayoutInflater mInflater;
    private List<HomeCampaign> mDatas;
    private List<HomeBean> mTextDatas;
    private Context mContext;
    private OnCampaignClickListenner mListener;


    public void setOnCampaignClickListenner(OnCampaignClickListenner listenner) {
        this.mListener = listenner;
    }

    public HomeCategoryAdapter(List<HomeCampaign> datas, Context context) {
        mDatas = datas;
        this.mContext = context;
    }

    public HomeCategoryAdapter(List<HomeBean> mDatas, Context context, int i) {
        mTextDatas = mDatas;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        mInflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_R) {
            return new HomeViewHolder(mInflater.inflate(R.layout.template_home_cardview2
                    , parent, false));
        }
        if (viewType == VIEW_TYPE_T) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_home_cardview3,parent,false);
            return new HomeTextHolder(view);
        } else {
            return new HomeViewHolder(mInflater.inflate(R.layout.template_home_cardview
                    , parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HomeViewHolder && position != getItemCount()) {
            HomeViewHolder viewHolder = (HomeViewHolder) holder;
            HomeCampaign category = mDatas.get(position);
            //        holder.textTitle.setText(category.getTitle());
//        holder.imageViewBig.setImageResource(category.getCpOne());
//        holder.imageViewSmallTop.setImageResource(category.getImgSmallTop());
//        holder.imageViewSmallBottom.setImageResource(category.getImgSmallBottom());

            // 从网上下载图片并装载到view里面
            viewHolder.textTitle.setText(category.getTitle());
            Picasso.with(mContext).load(category.getCpOne().getImgUrl()).into(viewHolder.imageViewBig);
            Picasso.with(mContext).load(category.getCpTwo().getImgUrl()).into(viewHolder.imageViewSmallTop);
            Picasso.with(mContext).load(category.getCpThree().getImgUrl()).into(viewHolder.imageViewSmallBottom);
        }
        if (position == getItemCount()){
            //加载我们
            HomeTextHolder homeTextHolder = (HomeTextHolder) holder;
            HomeBean homeBean = mTextDatas.get(position);
            homeTextHolder.homeTitle.setText("关于我们");
            homeTextHolder.homeDes.setText("地址：哈尔滨市南岗区黑龙江大学汽车维修店 客服电话：0451-00000000");
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return VIEW_TYPE_R;
        }
        else if(position % 2 != 0) {
            return VIEW_TYPE_L;
        } else
            return VIEW_TYPE_T;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textTitle;
        ImageView imageViewBig;
        ImageView imageViewSmallTop;
        ImageView imageViewSmallBottom;

        public HomeViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            imageViewBig = itemView.findViewById(R.id.imgview_big);
            imageViewSmallTop = itemView.findViewById(R.id.imgview_small_top);
            imageViewSmallBottom = itemView.findViewById(R.id.imgview_small_bottom);

            imageViewBig.setOnClickListener(this);
            imageViewSmallBottom.setOnClickListener(this);
            imageViewSmallTop.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            HomeCampaign homeCampaign = mDatas.get(getLayoutPosition());
            if (mListener != null) {
                switch (view.getId()) {
                    case R.id.imgview_big:
                        mListener.onClick(view, homeCampaign.getCpOne());
                        break;
                    case R.id.imgview_small_bottom:
                        mListener.onClick(view, homeCampaign.getCpThree());
                        break;
                    case R.id.imgview_small_top:
                        mListener.onClick(view, homeCampaign.getCpTwo());
                        break;
                }
            }
        }
    }

    class HomeTextHolder extends RecyclerView.ViewHolder {
        TextView homeTitle;
        TextView homeDes;

        public HomeTextHolder(View itemView) {
            super(itemView);
            homeTitle = itemView.findViewById(R.id.home_title);
            homeDes = itemView.findViewById(R.id.textview_home);
        }
    }

    public interface OnCampaignClickListenner {
        void onClick(View view, Campaign campaign);
    }
}
