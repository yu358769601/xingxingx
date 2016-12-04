package com.xinxinxuedai.view.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import com.xinxinxuedai.R;

/**
 * Created by 35876 于萌萌
 * 创建日期: 21:55 . 2016年12月04日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class my_radioButton extends RadioButton {
    public my_radioButton(Context context) {
        super(context);
        initView(context);
    }


    public my_radioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public my_radioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.my_radiobutton, null);

    }

}
