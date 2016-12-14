package com.xinxinxuedai.MVP.ApplyForActivity;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;
import com.xinxinxuedai.bean.GetInfo;

/**
 * Created by 35876 于萌萌
 * 创建日期: 8:57 . 2016年12月02日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface ApplyForActivity_callback extends BaseMVPinterFace_CallBack{
    /**
     * 用于显示
     */
    void setCallBackData(GetInfo getInfo);
}
