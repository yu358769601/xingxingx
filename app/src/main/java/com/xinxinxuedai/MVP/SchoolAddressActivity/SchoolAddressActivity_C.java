package com.xinxinxuedai.MVP.SchoolAddressActivity;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;
import com.xinxinxuedai.bean.GetInfo;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 16:09 . 2016年12月12日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface SchoolAddressActivity_C extends BaseMVPinterFace_CallBack{
    /**
     * 回显结果返回给activity
     * @param getInfo
     */
    void setCallBackData(GetInfo getInfo);
}
