package com.xinxinxuedai.MVP.ApplicationStatusActivity;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;
import com.xinxinxuedai.bean.GetLoanDetail;

/**
 * Created by 35876 于萌萌
 * 创建日期: 13:50 . 2016年12月06日
 * 描述:借款状态_C
 * <p>
 * <p>
 * 备注:
 */

public interface ApplicationStatusActivity_C extends BaseMVPinterFace_CallBack{
    /**
     * 给V的 数据设置
      */
    void setData(GetLoanDetail data);
}
