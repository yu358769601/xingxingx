package com.xinxinxuedai.MVP.LoanProductsActivity;

import com.xinxinxuedai.bean.GetLoanDetail;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:13 . 2016年12月02日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface LoanProductsActivity_CallBack {
    /**
     * 回调到 设置二选一的 图
     */
   void setCallBackData(GetLoanDetail callBackData);

    /**
     * 关掉当前 activity
     */
    void closeActivity();
}
