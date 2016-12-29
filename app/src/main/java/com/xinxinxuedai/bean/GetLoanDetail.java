package com.xinxinxuedai.bean;

import java.io.Serializable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:37 . 2016年12月12日
 * 描述:bean_获取借款详情
 * <p>
 * <p>
 * 备注:回显
 */

public class GetLoanDetail implements Serializable{


    //入口
//    hashtable.put("action", "getLoanDetail");
    //token
//    hashtable.put("loan_id", Share.getToken(context));
        /** 有拆分Data
         * token
         * id : 10475
         *
         * loan_bid_id : 0
         *
         * loan_userid : 10623
         *
         * loan_name :
         *
         * add_time : 2016-12-12   14:34:28
         *
         * money : 2222.00
         *
         * loan_service : 0.00
         *
         * loan_interest : 0.00
         *
         * loan_term : 3333
         *
         * loan_category : 1
         *
         * loan_describe : asd
         *
         * loan_small_describe : 1111测试0
         *
         * loan_status : 0
         *
         * loan_result :
         *
         * loan_plan : 1
         *
         * loan_pack : 0
         *
         * loan_from :
         *
         * fangkuan_time : 0
         *
         * again_flag : 0
         */

        /** 有拆分Data
         * token
         * id : 10475   借款id
         *
         * loan_bid_id : 打包进入的标的id
         *
         * loan_userid : 10623
         *
         * loan_name :用户名
         *
         * add_time : 2016-12-12   14:34:28
         *
         * money : 借的金额
         *
         * loan_service : 服务费
         *
         * loan_interest : 利息
         *
         * loan_term : 期限
         *
         * loan_category : 1
         *
         * loan_describe : 详细描述
         *
         * loan_small_describe : 简单描述  技能那些
         *
         *    0   strings.add("资料未完善");
              1   strings.add("借款审核中");// 不能进
              2   strings.add("借款审核已通过，请在24小时之内关注银行卡资金是否到账。"); //不能进
              3   strings.add("借款审核未通过");
              4   strings.add("借款放款成功,请按照还款计划及时归还借款。");//不能    //能进我要还款
              5   strings.add("借款放款失败");
              6   strings.add("借款还款已完成");//能进
              7   strings.add("借款已提前还款");
         * loan_status : 借款状态
         *
         * loan_result :审核结果
         *
         * loan_plan : 0先息后本  1等额本息
         *
         * loan_pack : 0 未打包  1打包
         *
         * loan_from :android  ios
         *
         * fangkuan_time : 放款时间
         *    //再分期新加的
         * again_flag : 0 是没有再分期  1 再分一期  2 再分二期  3 在分三期
         *
         */


        public String id;
        public String loan_bid_id;
        public String loan_userid;
        public String loan_name;
        public String add_time;
        public String money;
        public String loan_service;
        public String loan_interest;
        public int loan_term;
        public String loan_category;
        public String loan_describe;
        public String loan_small_describe;
        public int loan_status;
        public String loan_result;
        public String loan_plan;
        public String loan_pack;
        public String loan_from;
        public String fangkuan_time;
        public int again_flag;


        @Override
        public String toString() {
                return "GetLoanDetail{" +
                        "id='" + id + '\'' +
                        ", loan_bid_id='" + loan_bid_id + '\'' +
                        ", loan_userid='" + loan_userid + '\'' +
                        ", loan_name='" + loan_name + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", money='" + money + '\'' +
                        ", loan_service='" + loan_service + '\'' +
                        ", loan_interest='" + loan_interest + '\'' +
                        ", loan_term=" + loan_term +
                        ", loan_category='" + loan_category + '\'' +
                        ", loan_describe='" + loan_describe + '\'' +
                        ", loan_small_describe='" + loan_small_describe + '\'' +
                        ", loan_status=" + loan_status +
                        ", loan_result='" + loan_result + '\'' +
                        ", loan_plan='" + loan_plan + '\'' +
                        ", loan_pack='" + loan_pack + '\'' +
                        ", loan_from='" + loan_from + '\'' +
                        ", fangkuan_time='" + fangkuan_time + '\'' +
                        ", again_flag=" + again_flag +
                        '}';
        }
}

//{
//        "result": 1,
//        "message": "查询成功",
//        "data": {
//        "id": "10475",
//        "loan_bid_id": "0",
//        "loan_userid": "10623",
//        "loan_name": "",
//        "add_time": "2016-12-12   14:34:28",
//        "money": "2222.00",
//        "loan_service": "0.00",
//        "loan_interest": "0.00",
//        "loan_term": "3333",
//        "loan_category": "1",
//        "loan_describe": "asd",
//        "loan_small_describe": "1111测试0",
//        "again_flag":"0",
//        "loan_status": "0",
//        "loan_result": "",
//        "loan_plan": "1",
//        "loan_pack": "0",
//        "loan_from": "",
//        "fangkuan_time": "0"
//        }
//        }

