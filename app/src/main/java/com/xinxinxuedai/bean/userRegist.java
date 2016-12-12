package com.xinxinxuedai.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 9:56 . 2016年12月12日
 * 描述:bean_用户注册
 * <p>
 * <p>
 * 备注:
 */

public class userRegist {
    //入口
  //  hashtable.put("action", "userRegist");
    //电话号码
//    hashtable.put("loan_mobile",editText1.getText().toString().trim());
    //密码
//    hashtable.put("loan_pwd",editText3.getText().toString().trim());
    //重复密码
//    hashtable.put("loan_from",editText4.getText().toString().trim());
    //推荐码(可选)
//    hashtable.put("tuijianma","");

    /**
     * 结果码
     * result : 1
     * //结果信息
     * message : 用户注册成功
     * //token
     * data : {"user_id":10623}
     */

    public int result;
    public String message;
    public DataBean data;

    public static class DataBean {
        /**
         * user_id : 10623
         */

        public String user_id;
    }

    @Override
    public String toString() {
        return "userRegist{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
//    {
//        "result": 1,
//            "message": "用户注册成功",
//            "data": {"user_id": 10623}
//    }
}
