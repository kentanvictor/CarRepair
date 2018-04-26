package com.example.carrepair.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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
public class CToolBar extends Toolbar {
    private LayoutInflater mInflater;
    private View mView;
    private TextView mTextTitle;
    private EditText mSearchView;
    private Button mRightButton;

    private Context mContext ;

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public CToolBar(Context context) {
       this(context,null);
    }

    public CToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("RestrictedApi")
    public CToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);



        initView();
        setContentInsetsRelative(10,10);




        if(attrs !=null) {
            @SuppressLint("RestrictedApi") final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.CToolBar, defStyleAttr, 0);


            @SuppressLint("RestrictedApi") final Drawable rightIcon = a.getDrawable(R.styleable.CToolBar_rcightButtonIcon);
            if (rightIcon != null) {
                //setNavigationIcon(navIcon);
                setRightButtonIcon(rightIcon);
            }


            @SuppressLint("RestrictedApi") boolean isShowSearchView = a.getBoolean(R.styleable.CToolBar_icsShowSearchView,false);

            if(isShowSearchView){

                showSearchView();
                hideTitleView();

            }

            @SuppressLint("RestrictedApi") CharSequence rightButtonText = a.getText(R.styleable.CToolBar_rcightButtonText);
            if(rightButtonText !=null){
                setRightButtonText(rightButtonText);
            }

            a.recycle();
        }

    }

    private void initView() {
        if(mView == null) {
            mInflater = LayoutInflater.from(getContext());
            mView = mInflater.inflate(R.layout.toolbar, null);
            mTextTitle = mView.findViewById(R.id.toolbar_title);
            mSearchView = mView.findViewById(R.id.toolbar_searchview);
            mRightButton = mView.findViewById(R.id.toolbar_rightButton);

            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);

            addView(mView, lp);
            mSearchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actonId, KeyEvent keyEvent) {
                    //判断是否是search键
                    if (actonId == EditorInfo.IME_ACTION_SEARCH){
                        Log.d("editor","test");
                        String key = mSearchView.getText().toString().trim();
                        if (TextUtils.isEmpty(key)){
                            ToastUtils.show(getContext(),"请输入内容");
                            return true;
                        }
                        searchkey(key);
                    }
                    return false;
                }

            });
        }



    }

    private void searchkey(String key) {
        Intent intent = new Intent(getContext(), WaresListActivity.class);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void  setRightButtonIcon(Drawable icon){

        if(mRightButton !=null){

            mRightButton.setBackground(icon);
            mRightButton.setVisibility(VISIBLE);
        }

    }

    public void  setRightButtonIcon(int icon){

        setRightButtonIcon(getResources().getDrawable(icon));
    }


    public  void setRightButtonOnClickListener(OnClickListener li){

        mRightButton.setOnClickListener(li);
    }

    public void setRightButtonText(CharSequence text){
        mRightButton.setText(text);
        mRightButton.setVisibility(VISIBLE);
    }

    public void setRightButtonText(int id){
        setRightButtonText(getResources().getString(id));
    }

    public Button getRightButton(){

        return this.mRightButton;
    }

    @Override
    public void setTitle(int resId) {

        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title) {

        initView();
        if(mTextTitle !=null) {
            mTextTitle.setText(title);
            showTitleView();
        }
    }

    public  void showSearchView(){

        if(mSearchView !=null)
            mSearchView.setVisibility(VISIBLE);

    }

    public void hideSearchView(){
        if(mSearchView !=null)
            mSearchView.setVisibility(GONE);
    }

    public void showTitleView(){
        if(mTextTitle !=null)
            mTextTitle.setVisibility(VISIBLE);
    }

    public void hideTitleView() {
        if (mTextTitle != null)
            mTextTitle.setVisibility(GONE);

    }
}
