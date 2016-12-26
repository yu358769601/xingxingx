package com.xinxinxuedai.MVP.mainActivity;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;
import com.xinxinxuedai.bean.GetInfo;

/**
 * Created by 35876 于萌萌
 * 创建日期: 8:53 . 2016年12月02日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface MainActivity_CallBack extends BaseMVPinterFace_CallBack {
    /**
     * 左下角数据
     * @param dataMoney
     */
    void setDataMoney(GetInfo dataMoney);
}
