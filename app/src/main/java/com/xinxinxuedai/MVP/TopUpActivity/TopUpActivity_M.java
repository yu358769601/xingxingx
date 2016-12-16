package com.xinxinxuedai.MVP.TopUpActivity;

import android.widget.EditText;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;
import com.xinxinxuedai.ui.TopUpActivity;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 17:24 . 2016年12月07日
 * 描述:账户充值_M
 * <p>
 * <p>
 * 备注:
 */

public interface TopUpActivity_M {
    /**
     * 本页面所有的 输入框
     * @param editTextViews
     */
    void setEditTextViews(List<EditText> editTextViews);

    /**
     * 需要有activity
     */
    void serActivity(TopUpActivity activity);
}
