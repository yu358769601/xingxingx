package com.xinxinxuedai.MVP.TopUpActivity;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;

/**
 * Created by 35876 于萌萌
 * 创建日期: 17:25 . 2016年12月07日
 * 描述:账户充值_C
 * <p>
 * <p>
 * 备注:
 */

public interface TopUpActivity_C extends BaseMVPinterFace_CallBack {
    /**
     * 网络返回数据
     * @param s
     */
    void getData(String s);
}
