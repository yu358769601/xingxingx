package com.xinxinxuedai.MVP.LoginActivity;

import android.view.View;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;

/**
 * Created by 35876 于萌萌
 * 创建日期: 10:48 . 2016年12月04日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface LoginActivity_C extends BaseMVPinterFace_CallBack{
    /**
     * 在控制器里面点了什么按钮
     * @param view
     */
    void callBackButton(View view);
}
