package com.xinxinxuedai.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:45 . 2016年12月21日
 * 描述:提前还款bean 没有逾期的情况下是才会有按钮的 
 * <p>
 * <p>
 * 备注:
 */

public class UserLoanAdvanceMoney {
//    hashtable.put("action", "userLoanAdvanceMoney");
//    //这个地方必须是这个 之前接口问题
    //身份认证
//    hashtable.put("user_id", Share.getToken(context));
    //借款id
//    hashtable.put("loan_id",dataBean.user_loan_id);
    /**
     * data :
     * message : 账户资金不足
     * result : 0
     */

    public String data;
    public String message;
    public int result;
}

//{
//        "data": "",
//        "message": "账户资金不足",
//        "result": 0
//        }
