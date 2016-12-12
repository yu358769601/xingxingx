package com.xinxinxuedai.MVP.BankCardInfoActivity;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 35876 于萌萌
 * 创建日期: 14:13 . 2016年12月05日
 * 描述:银行卡信息_方法
 * <p>
 * <p>
 * 备注:
 */

public interface BankCardInfoActivity_M {
    /**
     * 获取 这个页面上所有的 editText
     * @param editTexts
     */
    void getEdtexts(ArrayList<TextView> editTexts, ArrayList<String> strings);
}
