package com.johnnytan.dell.nothelloworld.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/*
 * Created by JohnnyTan on 2018/3/16.
 */

public class EidtTextClearTools {
    public static void addClearListener(final EditText et, final ImageView iv) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int before) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = editable + "";
                if (editable.length() > 0) {
                    iv.setVisibility(View.VISIBLE);
                }
                else {
                    iv.setVisibility(View.INVISIBLE);
                }
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
            }
        });
    }
}
