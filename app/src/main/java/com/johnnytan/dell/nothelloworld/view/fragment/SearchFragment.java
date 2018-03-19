package com.johnnytan.dell.nothelloworld.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnnytan.dell.nothelloworld.R;

/*\
 * Created by JohnnyTan on 2018/3/17.
 */

public class SearchFragment extends Fragment{
    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View searchView = inflater.inflate(R.layout.fragment_search,container,false);
        return searchView;
    }
}
