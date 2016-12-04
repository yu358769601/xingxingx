package com.xinxinxuedai.MVP.LoginActivity;

import android.view.View;
import android.widget.EditText;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 10:47 . 2016年12月04日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface LoginActivity_M {
    //负责点击事件
    void Clicks(View view);

    /**
     * 负责获取 所有的填写数据的方法
     * @param editTextViews
     */
    void setEditTextViews(List<EditText> editTextViews);
}
