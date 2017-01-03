package com.xinxinxuedai.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 11:40 . 2016年12月12日
 * 描述:bean_获取借贷人信息 个人信息 学校信息 账户余额是在这个bean 里面的
 * <p>
 * <p>
 * 备注:回显
 */

public class GetInfo {

        /**
         * 有拆分data
         * 用户id
         * loan_id : 10623
         * 用户手机号
         * loan_mobile : 18603663028
         * 用户密码
         * loan_pwd : 25d55ad283aa400af464c76d713c07ad
         * 申请时间
         * loan_add_time : 1481510402
         * 来源
         * loan_from : 12345678
         * 账户余额
         * loan_money : 0.00
         * 性别
         * loan_sex :
         * 姓名
         * loan_realname :
         * 身份证
         * loan_card_id :
         * 微信号
         * loan_weixin :
         * 地址
         * loan_address :
         * 父母
         * loan_parent :
         * 父母电话
         * loan_parent_mobile :
         * 学校名
         * loan_school_name :
         * 入学年份
         * loan_admission_school :
         * 省
         * loan_province :
         * 市
         * loan_city :
         * 区域(华东)大区
         * loan_area :
         * 父母地址
         * loan_present_address :
         * 导师姓名
         * loan_tutor :
         * 导师电话
         * loan_tutor_mobile :
         * 银行卡号
         * loan_bank_card :
         * 银行卡名字
         * loan_bank_name :
         * 开户行
         * loan_bank_open :
         * 身份证1
         * loan_id_card_photo1 :
         * 身份证2
         * loan_id_card_photo2 :
         * 身份证3
         * loan_id_card_photo3 :
         * 视频1
         * loan_video1 :
         * 视频2
         * loan_video2 :
         * 推荐码
         * tuijianma :
         * 签到天数(新加)
         * sign_count_day :
         * 抽奖次数
         * draw_count : 0
         *
         * sign_time : 0
         *
         * xinyong_money : 0
         */

        public String loan_id;
        public String loan_mobile;
        public String loan_pwd;
        public String loan_add_time;
        public String loan_from;
        public double loan_money;
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

        @Override
        public String toString() {
                return "GetInfo{" +
                        "loan_id='" + loan_id + '\'' +
                        ", loan_mobile='" + loan_mobile + '\'' +
                        ", loan_pwd='" + loan_pwd + '\'' +
                        ", loan_add_time='" + loan_add_time + '\'' +
                        ", loan_from='" + loan_from + '\'' +
                        ", loan_money='" + loan_money + '\'' +
                        ", loan_sex='" + loan_sex + '\'' +
                        ", loan_realname='" + loan_realname + '\'' +
                        ", loan_card_id='" + loan_card_id + '\'' +
                        ", loan_weixin='" + loan_weixin + '\'' +
                        ", loan_address='" + loan_address + '\'' +
                        ", loan_parent='" + loan_parent + '\'' +
                        ", loan_parent_mobile='" + loan_parent_mobile + '\'' +
                        ", loan_school_name='" + loan_school_name + '\'' +
                        ", loan_admission_school='" + loan_admission_school + '\'' +
                        ", loan_province='" + loan_province + '\'' +
                        ", loan_city='" + loan_city + '\'' +
                        ", loan_area='" + loan_area + '\'' +
                        ", loan_present_address='" + loan_present_address + '\'' +
                        ", loan_tutor='" + loan_tutor + '\'' +
                        ", loan_tutor_mobile='" + loan_tutor_mobile + '\'' +
                        ", loan_bank_card='" + loan_bank_card + '\'' +
                        ", loan_bank_name='" + loan_bank_name + '\'' +
                        ", loan_bank_open='" + loan_bank_open + '\'' +
                        ", loan_id_card_photo1='" + loan_id_card_photo1 + '\'' +
                        ", loan_id_card_photo2='" + loan_id_card_photo2 + '\'' +
                        ", loan_id_card_photo3='" + loan_id_card_photo3 + '\'' +
                        ", loan_video1='" + loan_video1 + '\'' +
                        ", loan_video2='" + loan_video2 + '\'' +
                        ", tuijianma='" + tuijianma + '\'' +
                        ", sign_count_day='" + sign_count_day + '\'' +
                        ", draw_count='" + draw_count + '\'' +
                        ", sign_time='" + sign_time + '\'' +
                        ", xinyong_money='" + xinyong_money + '\'' +
                        '}';
        }
}
//{
//        "message": "查询成功",
//        "result": 1,
//        "data": {
//        "xinyong_money": "0",
//        "loan_money": "0.00",
//        "loan_bank_card": "1231231231",
//        "loan_tutor": "",
//        "loan_video1": "",
//        "loan_video2": "",
//        "loan_tutor_mobile": "",
//        "loan_admission_school": "",
//        "loan_province": "",
//        "loan_school_name": "",
//        "loan_city": "",
//        "loan_sex": "1",
//        "loan_card_id": "230103198908160914",
//        "loan_present_address": "",
//        "draw_count": "0",
//        "loan_address": "黑龙江哈尔滨0.02.00.0.0",
//        "loan_area": "",
//        "sign_count_day": "",
//        "loan_add_time": "1481510402",
//        "loan_id_card_photo3": "",
//        "loan_id_card_photo2": "",
//        "loan_from": "12345678",
//        "loan_id_card_photo1": "",
//        "loan_parent": "余",
//        "loan_realname": "玉田",
//        "loan_mobile": "18603663028",
//        "loan_bank_name": "水帘洞银行",
//        "loan_parent_mobile": "18603663029",
//        "loan_bank_open": "asdas",
//        "tuijianma": "",
//        "loan_pwd": "25d55ad283aa400af464c76d713c07ad",
//        "loan_weixin": "yu358769601",
//        "sign_time": "0",
//        "loan_id": "10623"
//        }
//        }