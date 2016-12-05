package com.xinxinxuedai.MVP.OrganizingDataActivity;

import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:45 . 2016年12月05日
 * 描述:完善信息_方法
 * <p>
 * <p>
 * 备注:
 */

public interface OrganizingDataActivity_M {
    /**
     * 获取 这个页面上所有的 textView
     * @param textViews
     */
    void getTextViews(List<TextView> textViews, EditText editText);
}
