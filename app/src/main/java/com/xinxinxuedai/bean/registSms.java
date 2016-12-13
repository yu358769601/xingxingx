package com.xinxinxuedai.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 9:51 . 2016年12月12日
 * 描述:bean_发送验证码
 * <p>
 * <p>
 * 备注:
 */

public class RegistSms {

//    hashtable.put("action","RegistSms");
    //电话号码
//    hashtable.put("loan_mobile",tag.getText().toString().trim());
    /**结果
     * result : 1
     * message : 验证码发送成功
     * data : {"code":"265673"}
     */

    public int result;
    public String message;
    public DataBean data;

    public static class DataBean {
        /**
         * code : 265673
         */

        public String code;
    }

    @Override
    public String toString() {
        return "RegistSms{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    //原始数据
//    {
//        "result": 1,
//            "message": "验证码发送成功",
//            "data": {"code": "265673"}
//    }

}
