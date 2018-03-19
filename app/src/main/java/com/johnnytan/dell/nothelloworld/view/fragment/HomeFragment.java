package com.johnnytan.dell.nothelloworld.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.johnnytan.dell.nothelloworld.bean.homebean.HomeItem;
import com.johnnytan.dell.nothelloworld.R;
import com.johnnytan.dell.nothelloworld.adapter.GirdDropDownAdapter;
import com.johnnytan.dell.nothelloworld.adapter.MyAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;

/*
 * Created by JohnnyTan on 2018/3/17.
 */

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeTest";
    //ViewPager
    private static final int[] drawableIds = new int[]{
            R.mipmap.ic_road, R.mipmap.ic_record, R.mipmap.ic_tire1, R.mipmap.ic_tire2, R.mipmap.ic_tire3
    };
    /**
     * 上一次高亮显示的位置
     */
    private int prePosition = 0;
    /**
     * 是否已经滚动
     */
    private boolean isDragging = false;
    private final String[] imageDescriptions = {
            "远足野营，其乐无穷",
            "记录真实场景",
            "固特异品牌日",
            "胎压检测",
            "轮胎其乐无穷"
    };
    private String headers[] = {"常规维修", "深度维修", "清洁维修"};
    //下拉菜单
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter normalsAdapter;
    private GirdDropDownAdapter deepsAdapter;
    private GirdDropDownAdapter cleansAdapter;
    private String normals[] = {"常规维修", "小型维修服务", "大型维修服务", "空调滤清器", "雨刷", "空调制冷剂", "更换防冻液"};
    private String deeps[] = {"深度维修", "刹车油", "火花塞", "正时皮带套装", "刹车盘", "刹车片", "大灯", "雾灯"};
    private String cleans[] = {"清洁维修", "发动机舱清洗", "发动机维修", "喷油嘴清洗", "三元催化清洗", "进气系统清洗", "水箱清洗", "水箱防锈系统维修", "刹车系统维修", "燃油系统维修", "蒸发箱清洗", "空调管路杀菌"};
    private DropDownMenu mDropDownMenu;
    private ArrayList<ImageView> imageViews;
    //ViewPager
    private ViewPager mViewPager;
    private TextView tv_title;
    private LinearLayout ll_point_group;
    //ListView的使用
    //1.在布局文件中定义ListView
    //2.在代码中实例化ListView
    //3.准备数据
    //4.设置适配器-item布局-绑定数据

    //CardView
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private ArrayList<HomeItem> mDataList;
    private static final int[] IMG = {R.mipmap.oil_castol_5w40, R.mipmap.oil_castrol_5w30, R.mipmap.oil_idemitsu, R.mipmap.oil_idemitsu_total, R.mipmap.oil_mobil_5w30, R.mipmap.oil_mobil_5w40,
            R.mipmap.oil_mobil_0240, R.mipmap.oil_mobil_minerals};
    private static final String[] TITLE = {"【品牌直供】嘉实多/Castrol 金嘉护矿物机油 5W-30 SN级（4L装）",
            "【正品行货】美孚/Mobil 速霸1000矿物机油 5W-30 SN级（4L装）",
            "【正品行货】美孚/Mobil 美孚1号全合成机油 5W-30 SN级（4L装）",
            "【正品行货】美孚/Mobil 速霸2000半合成机油 5W-30 SN级（4L装）",
            "【正品行货】美孚/Mobil 美孚1号全合成机油 5W-40 SN级（4L装）",
            "【正品行货】美孚/Mobil 速霸1000矿物机油 10W-40 SN级（4L装）",
            "【正品行货】美孚/Mobil 美孚1号全合成机油 0W-20 SN级（4L装）",
            "【品牌直供】嘉实多/Castrol 极护全合成机油 5W-30 SN A5/B5（4L装）"};

    private List<String> titleList = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = mViewPager.getCurrentItem() + 1;
            mViewPager.setCurrentItem(item);
            //延迟发消息
            handler.sendEmptyMessageDelayed(0, 4000);
        }
    };

    @NonNull
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        return homeView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initViews(view);
        titleList = Arrays.asList(TITLE);
    }

    private void initViews(View view) {
        mViewPager = view.findViewById(R.id.viewpager);
        tv_title = view.findViewById(R.id.tv_title);
        ll_point_group = view.findViewById(R.id.ll_point_group);
        mDropDownMenu = view.findViewById(R.id.dropDownMenu);
      /*  mRecyclerView = view.findViewById(R.id.rl_CardView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/
        //init normal menu
        final ListView normalView = new ListView(getContext());
        normalsAdapter = new GirdDropDownAdapter(getContext(), Arrays.asList(normals));
        normalView.setDividerHeight(0);
        normalView.setAdapter(normalsAdapter);
        //init deep menu
        final ListView deepView = new ListView(getContext());
        deepsAdapter = new GirdDropDownAdapter(getContext(), Arrays.asList(deeps));
        deepView.setDividerHeight(0);
        deepView.setAdapter(deepsAdapter);
        //init clean menu
        final ListView cleanView = new ListView(getContext());
        cleansAdapter = new GirdDropDownAdapter(getContext(), Arrays.asList(cleans));
        cleanView.setDividerHeight(0);
        cleanView.setAdapter(cleansAdapter);
        //init popupViews
        popupViews.add(normalView);
        popupViews.add(deepView);
        popupViews.add(cleanView);
        //add item click event
        normalView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                normalsAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : normals[position]);
                mDropDownMenu.closeMenu();
                if (normals[position].equals("雨刷")) {
                    for (int i = 0; i < TITLE.length; i++) {
                        HomeItem item = new HomeItem();
                        item.setTitle(TITLE[i]);
                        item.setImageResource(IMG[i]);
                        mDataList.add(item);
                    }
                }

            }
        });

        deepView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deepsAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : deeps[position]);
                mDropDownMenu.closeMenu();
            }
        });

        cleanView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cleansAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : cleans[position]);
                mDropDownMenu.closeMenu();
            }
        });
        //init context view
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //init
        mDataList = new ArrayList<>();

        Log.d("test", "IMG length:" + IMG.length);
        myAdapter = new MyAdapter(R.layout.item_recycle_cardview, mDataList);
        myAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        myAdapter.isFirstOnly(false);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, recyclerView);

        /**
         * ViewPager的使用
         * 1.在布局文件中定义ViewPager
         * 2.在代码中实例化ViewPager
         * 3.准备数据
         */
        imageViews = new ArrayList<>();
        for (int i = 0; i < drawableIds.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(drawableIds[i]);//添加到集合中(这个数据可以来自于网络)
            //添加到集合中
            imageViews.add(imageView);
            //添加点
            ImageView point = new ImageView(getContext());
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i == 0) {
                point.setEnabled(true);//显示红色
            } else {
                point.setEnabled(false);//显示灰色
                params.leftMargin = 8;
            }
            point.setLayoutParams(params);
            ll_point_group.addView(point);
        }
        //4.设置适配器(PagerAdapter)-item布局-绑定数据
        mViewPager.setAdapter(new HomeViewPager());
        //设置监听ViewPager页面的改变
        mViewPager.addOnPageChangeListener(new HomeViewPager());
        //设置中间位置
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViews.size();//要保证imageViews的整数倍
        mViewPager.setCurrentItem(item);
        tv_title.setText(imageDescriptions[prePosition]);
        //发消息
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    class HomeViewPager extends PagerAdapter implements ViewPager.OnPageChangeListener {
        /**
         * 当页面滚动了的时候回调这个方法
         *
         * @param position             当前页面的位置
         * @param positionOffset       滑动页面的百分比
         * @param positionOffsetPixels 在屏幕上滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        /**
         * 当某个页面被选中了的时候回调
         *
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            int realPosition = position % imageViews.size();
            //设置对应页面的文本信息
            tv_title.setText(imageDescriptions[realPosition]);
            //把上一个高亮的设置默认-灰色
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            //当前的设置为高亮-红色
            ll_point_group.getChildAt(realPosition).setEnabled(true);
            prePosition = realPosition;
        }

        /**
         * 当页面滚动状态变化的时候回调这个方法
         * 静止->滑动
         * 滑动-->静止
         * 静止-->拖拽
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                isDragging = true;
                handler.removeCallbacksAndMessages(null);
                Log.e(TAG, "SCROLL_STATE_DRAGGING-------------------");
            } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                Log.e(TAG, "SCROLL_STATE_SETTLING-----------------");

            } else if (state == ViewPager.SCROLL_STATE_IDLE && isDragging) {
                isDragging = false;
                Log.e(TAG, "SCROLL_STATE_IDLE------------");
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0, 4000);
            }
        }

        /**
         * 得到图片的总数
         *
         * @return
         */
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /**
         * 相当于getView方法
         *
         * @param container ViewPager自身
         * @param position  当前实例化页面的位置
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            int realPosition = position % imageViews.size();

            final ImageView imageView = imageViews.get(realPosition);
            container.addView(imageView);


            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN://手指按下
                            Log.e(TAG, "onTouch==手指按下");
                            handler.removeCallbacksAndMessages(null);
                            break;

                        case MotionEvent.ACTION_MOVE://手指在这个控件上移动
                            break;
                        case MotionEvent.ACTION_CANCEL://手指在这个控件上移动
                            Log.e(TAG, "onTouch==事件取消");

                            break;
                        case MotionEvent.ACTION_UP://手指离开
                            Log.e(TAG, "onTouch==手指离开");
                            handler.removeCallbacksAndMessages(null);
                            handler.sendEmptyMessageDelayed(0, 4000);
                            break;
                    }
                    return false;
                }
            });

            imageView.setTag(position);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "点击事件");
                    int position = (int) v.getTag() % imageViews.size();
                    String text = imageDescriptions[position];
                }
            });

            return imageView;
        }

        /**
         * 比较view和object是否同一个实例
         *
         * @param view   页面
         * @param object 这个方法instantiateItem返回的结果
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 释放资源
         *
         * @param container viewpager
         * @param position  要释放的位置
         * @param object    要释放的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }

    }


    @Override
    public void onStop() {
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onStop();
        }
    }

}
