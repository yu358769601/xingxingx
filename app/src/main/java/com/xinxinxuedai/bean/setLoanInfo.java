package com.xinxinxuedai.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:14 . 2016年12月12日
 * 描述:bean_添加用户借款信息
 * <p>
 * <p>
 * 备注:
 */

public class setLoanInfo {
//    //id
//    hashtable.put("loan_userid","0");
//    //多少钱
//    hashtable.put("money","0");
//    //期限
//    hashtable.put("loan_term","0");
//    //借款类型 1 有很多类型 (以后扩展功能)
//    hashtable.put("loan_category","0");
//    //借款描述
//    hashtable.put("loan_describe","0");
//    //借款简略描述
//    hashtable.put("loan_small_describe","0");
//    //判断怎么进来的 (二选一) 0 先息后本  1 等额本息
//    hashtable.put("loan_plan","0");
    //入口
 //   hashtable.put("action", "setLoanInfo");
    /**
     * result : 1
     * message : 借款申请提交成功
     * data :
     */

    public int result;
    public String message;
    public String data;

    @Override
    public String toString() {
        return "setLoanInfo{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
    //    {
//        "result": 1,
//            "message": "借款申请提交成功",
//            "data": ""
//    }
}
