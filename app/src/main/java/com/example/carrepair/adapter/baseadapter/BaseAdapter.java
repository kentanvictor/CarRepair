package com.example.carrepair.adapter.baseadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseAdapter封装
 * @param <T> 数据类型
 * @param <H> BaseViewHolder
 */
public abstract class BaseAdapter<T, H extends BaseViewHolder> extends
        RecyclerView.Adapter<BaseViewHolder> {
    protected static final String TAG = BaseAdapter.class.getSimpleName();
    protected final Context context;
    protected final int layoutResId;
    protected List<T> datas;


    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public BaseAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }


    public BaseAdapter(Context context, int layoutResId, List<T> datas) {
        this.datas = datas == null ? new ArrayList<T>() : datas;
        this.context = context;
        this.layoutResId = layoutResId;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        BaseViewHolder vh = new BaseViewHolder(view, mOnItemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHoder, int position) {
        T item = getItem(position);
        convert((H) viewHoder, item);
    }


    @Override
    public int getItemCount() {
        if (datas == null || datas.size() <= 0)
            return 0;
        return datas.size();
    }

    /**
     * 得到item
     *
     * @param position 第几个item
     * @return
     */
    public T getItem(int position) {
        if (position >= datas.size()) return null;
        return datas.get(position);
    }

    /**
     * 删除数据
     */
    public void clearData() {
        int itemCount = datas.size();
        datas.clear();
        this.notifyItemRangeRemoved(0, itemCount);
    }

    /**
     * 得到数据
     *
     * @return
     */
    public List<T> getDatas() {

        return datas;
    }

    public void addData(List<T> datas) {

        addData(0, datas);
    }

    /**
     * 添加数据
     *
     * @param position
     * @param datas
     */
    public void addData(int position, List<T> datas) {
        if (datas != null && datas.size() > 0) {

            this.datas.addAll(datas);
            this.notifyItemRangeChanged(position, datas.size());
        }
    }


    /**
     * 实现此方法并使用帮助器将视图调整为给定项目。
     *
     * @param viewHoder 初始化viewhodler
     * @param item      需要显示的项目
     */
    protected abstract void convert(H viewHoder, T item);


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;

    }


}
