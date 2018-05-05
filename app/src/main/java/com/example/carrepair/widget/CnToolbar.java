package com.example.carrepair.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.carrepair.R;
import com.example.carrepair.activity.WaresListActivity;
import com.example.carrepair.utils.ToastUtils;

/**
 * 自定义Toolbar
 * 1、自定义布局，获取布局并添加相应功能，继承Toolbar
 * 2、引用自定义属性：创建attribute文件，并添加相应控件，并在toolbar进行读写
 * 3、为自定义控件添加监听事件
 */
public class CnToolbar extends Toolbar implements View.OnClickListener {
    private LayoutInflater mInflater;
    private View mView;
    private TextView mTextTitle;
    private EditText mSearchView;
    private String searchTest;
    private Button mRightButton;
    private ImageButton mLeftButton;
    private Context mContext;


    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


    public CnToolbar(Context context) {
        this(context, null);
    }

    public CnToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("RestrictedApi")
    public CnToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化控件
        initView();
        // toolbar两边边距
        setContentInsetsRelative(10, 10);


        if (attrs != null) {
            /**
             * 读取自定义属性
             */
            @SuppressLint("RestrictedApi") final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.CnToolbar, defStyleAttr, 0);

            //右边按钮图片
            @SuppressLint("RestrictedApi") final Drawable rightIcon = a.getDrawable(R.styleable.CnToolbar_rightButtonIcon);
            if (rightIcon != null) {
                //setNavigationIcon(navIcon);
                setRightButtonIcon(rightIcon);
            }

            //搜索框
            @SuppressLint("RestrictedApi") boolean isShowSearchView = a.getBoolean(R.styleable.CnToolbar_isShowSearchView
                    , false);
            if (isShowSearchView) {
                showSearchView();
                hideTitleView();
            }

            //资源回收
            a.recycle();
        }

    }
    /**
     * 初始化控件
     */
    private void initView() {

        //避免控件添加进去为空，setTitle()在构造函数没有调用，因此可能为空，需要为空判断
        if (mView == null) {
            mInflater = LayoutInflater.from(getContext());
            mView = mInflater.inflate(R.layout.toolbar, null);
            mTextTitle = mView.findViewById(R.id.toolbar_title);
            mSearchView = mView.findViewById(R.id.toolbar_searchview);
            mRightButton = mView.findViewById(R.id.toolbar_rightButton);
            //---------------------
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mView, lp);
            //设置搜索的点击事件
            mSearchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        String key = mSearchView.getText().toString();
                        if (TextUtils.isEmpty(key)) {
                            ToastUtils.show(getContext(), "请输入内容");
                        }
                        searchKey(key);
                        return true;
                    }
                    return false;
                }
            });
            mSearchView.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mSearchView.setHint("");
                    } else {
                        mSearchView.setHint("请输入搜索内容");
                    }
                }
            });
            mSearchView.setHint("请输入搜索内容");
        }


    }

    private void searchKey(String key) {
        if (key.contains("美容")) {
            WaresListActivity.waresList = 0;
            Intent intent = new Intent(getContext(), WaresListActivity.class);
            getContext().startActivity(intent);
        }
        if (key.contains("维修")) {
            WaresListActivity.waresList = 1;
            Intent intent = new Intent(getContext(), WaresListActivity.class);
            getContext().startActivity(intent);
        }
        if (key.contains("人工")) {
            WaresListActivity.waresList = 2;
            Intent intent = new Intent(getContext(), WaresListActivity.class);
            getContext().startActivity(intent);
        }
    }


    public void setRightButtonIcon(Drawable icon) {

        if (mRightButton != null) {

            mRightButton.setBackground(icon);
            mRightButton.setVisibility(VISIBLE);
        }

    }

    // button监听事件
    public void setRightButtonOnClickListener(OnClickListener li) {
        mRightButton.setOnClickListener(li);
    }


    public void setleftButtonIcon(Drawable icon) {
        if (icon != null) {
            mLeftButton.setImageDrawable(icon);
            showRightButton();
        }
    }

    public void setleftButtonIcon(int id) {
        setleftButtonIcon(getResources().getDrawable(id));
    }

    /**
     * 重写setTitle()方法
     *
     * @param resId 标题资源id
     */
    @Override
    public void setTitle(int resId) {

        setTitle(getContext().getText(resId));
    }
    /**
     * 重写setTitle()方法
     *
     * @param title 标题名
     */
    @Override
    public void setTitle(CharSequence title) {
        initView();
        if (mTextTitle != null) {
            mTextTitle.setText(title);
            showTitleView();
        }
    }

    /**
     * 显示搜索框
     */
    public void showSearchView() {

        if (mSearchView != null)
            mSearchView.setVisibility(VISIBLE);

    }

    /**
     * 隐藏搜索框
     */
    public void hideSearchView() {
        if (mSearchView != null)
            mSearchView.setVisibility(GONE);
    }
    /**
     * 显示title
     */
    public void showTitleView() {
        if (mTextTitle != null)
            mTextTitle.setVisibility(VISIBLE);
    }

    /**
     * 隐藏title
     */
    public void hideTitleView() {
        if (mTextTitle != null)
            mTextTitle.setVisibility(GONE);

    }

    /**
     * 获取按钮
     *
     * @return
     */
    public Button getRightButton() {

        return this.mRightButton;
    }

    /**
     * 设置按钮文字
     *
     * @param text
     */
    public void setRightButtonText(CharSequence text) {
        mRightButton.setText(text);
        mRightButton.setVisibility(VISIBLE);
    }


    public void setRightButtonText(int id) {
        setRightButtonText(getResources().getString(id));
    }


    /**
     * 显示右边按钮
     */
    public void showRightButton() {
        if (mRightButton != null)
            mRightButton.setVisibility(VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_searchview:
                searchTest = mSearchView.getText().toString();
                if (searchTest.contains("机油")) {
                    WaresListActivity.waresList = 0;
                }
                break;

        }
    }
}
