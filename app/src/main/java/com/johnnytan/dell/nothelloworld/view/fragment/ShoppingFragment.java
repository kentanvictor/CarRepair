package com.johnnytan.dell.nothelloworld.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnnytan.dell.nothelloworld.R;

/**
 * Created by JohnnyTan on 2018/3/17.
 */

public class ShoppingFragment extends Fragment{
    public static ShoppingFragment newInstance(){
        return new ShoppingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View shoppingView = inflater.inflate(R.layout.fragment_shopping,container,false);
        return shoppingView;
    }
}
