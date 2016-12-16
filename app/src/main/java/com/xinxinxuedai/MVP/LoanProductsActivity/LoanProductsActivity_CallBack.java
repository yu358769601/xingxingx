package com.xinxinxuedai.MVP.LoanProductsActivity;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;
import com.xinxinxuedai.bean.GetLoanDetail;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:13 . 2016年12月02日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface LoanProductsActivity_CallBack extends BaseMVPinterFace_CallBack{
    /**
     * 回调到 设置二选一的 图
     */
   void setCallBackData(GetLoanDetail callBackData);

}
