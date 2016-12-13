package com.xinxinxuedai.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 10:01 . 2016年12月12日
 * 描述:bean_用户登陆
 * <p>
 * <p>
 * 备注:
 */

public class UserLogin {
    //入口
    //hashtable.put("action", "UserLogin");
    //手机号码
//    hashtable.put("loan_mobile",editText1.getText().toString().trim());
    //密码
//    hashtable.put("loan_pwd",editText2.getText().toString().trim());
    /**
     * result : 1
     * message : 登录成功
     * data : {"loan_id":"10623","loan_mobile":"18603663028","loan_pwd":"25d55ad283aa400af464c76d713c07ad","loan_add_time":"1481510402","loan_from":"12345678","loan_money":"0.00","loan_sex":"","loan_realname":"","loan_card_id":"","loan_weixin":"","loan_address":"","loan_parent":"","loan_parent_mobile":"","loan_school_name":"","loan_admission_school":"","loan_province":"","loan_city":"","loan_area":"","loan_present_address":"","loan_tutor":"","loan_tutor_mobile":"","loan_bank_card":"","loan_bank_name":"","loan_bank_open":"","loan_id_card_photo1":"","loan_id_card_photo2":"","loan_id_card_photo3":"","loan_video1":"","loan_video2":"","tuijianma":"","sign_count_day":"","draw_count":"0","sign_time":"0","xinyong_money":"0","loan_status":""}
     */

    public int result;
    public String message;
    public DataBean data;

    public static class DataBean {
        /**
         * token
         * loan_id : 10623
         *
         * 手机号码
         * loan_mobile : 18603663028
         *
         * 手机密码
         * loan_pwd : 25d55ad283aa400af464c76d713c07ad
         *
         * 添加时间
         * loan_add_time : 1481510402
         *
         * 重复
         * loan_from : 12345678
         *
         *
         * loan_money : 0.00
         *
         *
         * loan_sex :
         * loan_realname :
         * loan_card_id :
         * loan_weixin :
         * loan_address :
         * loan_parent :
         * loan_parent_mobile :
         * loan_school_name :
         * loan_admission_school :
         * loan_province :
         * loan_city :
         * loan_area :
         * loan_present_address :
         * loan_tutor :
         * loan_tutor_mobile :
         * loan_bank_card :
         * loan_bank_name :
         * loan_bank_open :
         * loan_id_card_photo1 :
         * loan_id_card_photo2 :
         * loan_id_card_photo3 :
         * loan_video1 :
         * loan_video2 :
         * tuijianma :
         * sign_count_day :
         * draw_count : 0
         * sign_time : 0
         * xinyong_money : 0
         * loan_status :
         */

        public String loan_id;
        public String loan_mobile;
        public String loan_pwd;
        public String loan_add_time;
        public String loan_from;
        public String loan_money;
        public String loan_sex;
        public String loan_realname;
        public String loan_card_id;
        public String loan_weixin;
        public String loan_address;
        public String loan_parent;
        public String loan_parent_mobile;
        public String loan_school_name;
        public String loan_admission_school;
        public String loan_province;
        public String loan_city;
        public String loan_area;
        public String loan_present_address;
        public String loan_tutor;
        public String loan_tutor_mobile;
        public String loan_bank_card;
        public String loan_bank_name;
        public String loan_bank_open;
        public String loan_id_card_photo1;
        public String loan_id_card_photo2;
        public String loan_id_card_photo3;
        public String loan_video1;
        public String loan_video2;
        public String tuijianma;
        public String sign_count_day;
        public String draw_count;
        public String sign_time;
        public String xinyong_money;
        public String loan_status;
    }


//    {
//        "result": 1,
//            "message": "登录成功",
//            "data": {
//        "loan_id": "10623",
//                "loan_mobile": "18603663028",
//                "loan_pwd": "25d55ad283aa400af464c76d713c07ad",
//                "loan_add_time": "1481510402",
//                "loan_from": "12345678",
//                "loan_money": "0.00",
//                "loan_sex": "",
//                "loan_realname": "",
//                "loan_card_id": "",
//                "loan_weixin": "",
//                "loan_address": "",
//                "loan_parent": "",
//                "loan_parent_mobile": "",
//                "loan_school_name": "",
//                "loan_admission_school": "",
//                "loan_province": "",
//                "loan_city": "",
//                "loan_area": "",
//                "loan_present_address": "",
//                "loan_tutor": "",
//                "loan_tutor_mobile": "",
//                "loan_bank_card": "",
//                "loan_bank_name": "",
//                "loan_bank_open": "",
//                "loan_id_card_photo1": "",
//                "loan_id_card_photo2": "",
//                "loan_id_card_photo3": "",
//                "loan_video1": "",
//                "loan_video2": "",
//                "tuijianma": "",
//                "sign_count_day": "",
//                "draw_count": "0",
//                "sign_time": "0",
//                "xinyong_money": "0",
//                "loan_status": ""
//    }
//    }
}
