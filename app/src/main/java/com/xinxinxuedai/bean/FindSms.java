package com.xinxinxuedai.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 10:01 . 2016年12月12日
 * 描述:bean_修改密码的时候的验证码
 * <p>x
 * <p>
 * 备注:
 */

public class FindSms {
    //手机号
    //hashtable.put("loan_mobile",tag.getText().toString().trim());
    /**
     * message : 验证码发送成功
     * result : 1
     * data : {"code":"248370"}
     */

    public String message;
    public int result;
    public DataBean data;

    public static class DataBean {
        /**
         * code : 248370
         */

        public String code;
    }

    @Override
    public String toString() {
        return "FindSms{" +
                "message='" + message + '\'' +
                ", result=" + result +
                ", data=" + data +
                '}';
    }
    //    {
//        "message": "验证码发送成功",
//            "result": 1,
//            "data": {"code": "248370"}
//    }
}
