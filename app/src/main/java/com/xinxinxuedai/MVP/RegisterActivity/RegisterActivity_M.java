package com.xinxinxuedai.MVP.RegisterActivity;

import android.view.View;
import android.widget.EditText;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 11:15 . 2016年12月01日
 * 描述:注册页面 使用的方法
 * <p>
 * <p>
 * 备注:
 */

public interface RegisterActivity_M {
    //点击事件
    void onClick(View view);
    //开启服务
    void startService();
    /**
     * 负责获取 所有的填写数据的方法
     * @param editTextViews
     * @param classtag
     */
    void setEditTextViews(List<EditText> editTextViews, int classtag);
}
