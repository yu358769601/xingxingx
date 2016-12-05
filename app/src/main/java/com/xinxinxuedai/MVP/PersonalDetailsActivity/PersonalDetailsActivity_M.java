package com.xinxinxuedai.MVP.PersonalDetailsActivity;

import android.widget.EditText;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 12:38 . 2016年12月05日
 * 描述:个人信息_方法
 * <p>
 * <p>
 * 备注:
 */

public interface PersonalDetailsActivity_M {
    /**
     * 获取 这个页面上所有的 editText 还有一个 radiobutton 选择的号码
     * @param editTexts
     * @param select
     */
    void getEdtexts(List<EditText> editTexts,int select);
}
