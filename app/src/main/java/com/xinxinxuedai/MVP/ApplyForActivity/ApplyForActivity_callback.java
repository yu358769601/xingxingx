package com.xinxinxuedai.MVP.ApplyForActivity;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.bean.GetInfoShow;

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
     * 用于获取到 下一级页面的所有数据
     */
    void setCallBackData(GetInfo getInfo);
    /**
     * 用于获取到 本页面的星星
     */
    void setCallBackData(GetInfoShow getInfoShow);
}
