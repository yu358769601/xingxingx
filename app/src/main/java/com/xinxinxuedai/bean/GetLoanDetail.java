package com.xinxinxuedai.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:37 . 2016年12月12日
 * 描述:bean_获取借款详情
 * <p>
 * <p>
 * 备注:回显
 */

public class GetLoanDetail implements Serializable{


        /**
         * data : {"loan_from":"android","loan_category":"1","loan_small_describe":"培训","money":"800.00","loan_plan":"1","loan_describe":"测试","loan_interest":"0.00","loan_bid_id":"164","add_time":"2017-01-04   14:31:29","loan_userid":"11947","loan_name":"","loan_term":"84","loan_result":"","id":"12058","loan_pack":"1","fangkuan_time":"1483512310","again_flag":"3","again":[{"again_term":"56","again_pack":"1","again_bid_id":"165","again_fangkuan":"1483514223","plan_id":"10200","again_time":"1483514141","again_fuwu":"22.00","id":"17","loan_user_id":"11947","again_flag":"2","loan_id":"12058","again_money":"267.00","again_lixi":"8.00","again_shouxufei":"26.70"},{"again_term":"56","again_pack":"1","again_bid_id":"166","again_fangkuan":"1483517836","plan_id":"10204","again_time":"1483514687","again_fuwu":"22.00","id":"18","loan_user_id":"11947","again_flag":"2","loan_id":"12058","again_money":"267.00","again_lixi":"8.00","again_shouxufei":"26.70"},{"again_term":"56","again_pack":"1","again_bid_id":"167","again_fangkuan":"1483518591","plan_id":"10208","again_time":"1483518483","again_fuwu":"22.00","id":"19","loan_user_id":"11947","again_flag":"2","loan_id":"12058","again_money":"266.00","again_lixi":"8.00","again_shouxufei":"26.60"}],"loan_status":"6","loan_service":"0.00"}
         * message : 查询成功
         * result : 1
         */


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
         *    //再分期新加的   先判断
         * again_flag : 0 是没有再分期  1 再分一期  2 再分二期  3 在分三期
         *
         */


                public String loan_from;
                public String loan_category;
                public String loan_small_describe;
                public String money;
                public String loan_plan;
                public String loan_describe;
                public String loan_interest;
                public String loan_bid_id;
                public String add_time;
                public String loan_userid;
                public String loan_name;
                public int loan_term;
                public String loan_result;
                public String id;
                public String loan_pack;
                public String fangkuan_time;
                public int again_flag;
                public int loan_status;
                public String loan_service;
                public List<AgainBean> again;

        @Override
        public String toString() {
                return "GetLoanDetail{" +
                        "loan_from='" + loan_from + '\'' +
                        ", loan_category='" + loan_category + '\'' +
                        ", loan_small_describe='" + loan_small_describe + '\'' +
                        ", money='" + money + '\'' +
                        ", loan_plan='" + loan_plan + '\'' +
                        ", loan_describe='" + loan_describe + '\'' +
                        ", loan_interest='" + loan_interest + '\'' +
                        ", loan_bid_id='" + loan_bid_id + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", loan_userid='" + loan_userid + '\'' +
                        ", loan_name='" + loan_name + '\'' +
                        ", loan_term='" + loan_term + '\'' +
                        ", loan_result='" + loan_result + '\'' +
                        ", id='" + id + '\'' +
                        ", loan_pack='" + loan_pack + '\'' +
                        ", fangkuan_time='" + fangkuan_time + '\'' +
                        ", again_flag='" + again_flag + '\'' +
                        ", loan_status='" + loan_status + '\'' +
                        ", loan_service='" + loan_service + '\'' +
                        ", again=" + again +
                        '}';
        }

        public static class AgainBean  implements Serializable{
                        /**分期期限
                         * again_term : 56
                         *
                         *
                         * again_pack : 1
                         *
                         *
                         * again_bid_id : 165
                         *
                         * 放款时间
                         * again_fangkuan : 1483514223
                         *
                         *
                         * plan_id : 10200
                         *
                         * 申请时间
                         * again_time : 1483514141
                         *
                         * 服务费
                         * again_fuwu : 22.00
                         *
                         *
                         * id : 17
                         *
                         *
                         * loan_user_id : 11947
                         *
                         * 审核状态 0 待审核 1 审核通过 2 满标放款成功
                         * again_flag : 2
                         *
                         *
                         * loan_id : 12058
                         *
                         *分期的钱
                         * again_money : 267.00
                         *
                         * 利息
                         * again_lixi : 8.00
                         *
                         * 手续费
                         * again_shouxufei : 26.70
                         */

                        public String again_term;
                        public String again_pack;
                        public String again_bid_id;
                        public long again_fangkuan;
                        public String plan_id;
                        public long again_time;
                        public String again_fuwu;
                        public String id;
                        public String loan_user_id;
                        public int again_flag;
                        public String loan_id;
                        public double again_money;
                        public String again_lixi;
                        public String again_shouxufei;

                @Override
                public String toString() {
                        return "AgainBean{" +
                                "again_term='" + again_term + '\'' +
                                ", again_pack='" + again_pack + '\'' +
                                ", again_bid_id='" + again_bid_id + '\'' +
                                ", again_fangkuan='" + again_fangkuan + '\'' +
                                ", plan_id='" + plan_id + '\'' +
                                ", again_time='" + again_time + '\'' +
                                ", again_fuwu='" + again_fuwu + '\'' +
                                ", id='" + id + '\'' +
                                ", loan_user_id='" + loan_user_id + '\'' +
                                ", again_flag='" + again_flag + '\'' +
                                ", loan_id='" + loan_id + '\'' +
                                ", again_money='" + again_money + '\'' +
                                ", again_lixi='" + again_lixi + '\'' +
                                ", again_shouxufei='" + again_shouxufei + '\'' +
                                '}';
                }
        }
        }


//
//        public String id;
//        public String loan_bid_id;
//        public String loan_userid;
//        public String loan_name;
//        public String add_time;
//        public String money;
//        public String loan_service;
//        public String loan_interest;
//        public int loan_term;
//        public String loan_category;
//        public String loan_describe;
//        public String loan_small_describe;
//        public int loan_status;
//        public String loan_result;
//        public String loan_plan;
//        public String loan_pack;
//        public String loan_from;
//        public String fangkuan_time;
//        public int again_flag;
//
//



        //最新json
//{
//        "data": {
//        "loan_from": "android",
//        "loan_category": "1",
//        "loan_small_describe": "培训",
//        "money": "800.00",
//        "loan_plan": "1",
//        "loan_describe": "测试",
//        "loan_interest": "0.00",
//        "loan_bid_id": "164",
//        "add_time": "2017-01-04   14:31:29",
//        "loan_userid": "11947",
//        "loan_name": "",
//        "loan_term": "84",
//        "loan_result": "",
//        "id": "12058",
//        "loan_pack": "1",
//        "fangkuan_time": "1483512310",
//        "again_flag": "3",
//        "again": [
//        {
//        "again_term": "56",
//        "again_pack": "1",
//        "again_bid_id": "165",
//        "again_fangkuan": "1483514223",
//        "plan_id": "10200",
//        "again_time": "1483514141",
//        "again_fuwu": "22.00",
//        "id": "17",
//        "loan_user_id": "11947",
//        "again_flag": "2",
//        "loan_id": "12058",
//        "again_money": "267.00",
//        "again_lixi": "8.00",
//        "again_shouxufei": "26.70"
//        },
//        {
//        "again_term": "56",
//        "again_pack": "1",
//        "again_bid_id": "166",
//        "again_fangkuan": "1483517836",
//        "plan_id": "10204",
//        "again_time": "1483514687",
//        "again_fuwu": "22.00",
//        "id": "18",
//        "loan_user_id": "11947",
//        "again_flag": "2",
//        "loan_id": "12058",
//        "again_money": "267.00",
//        "again_lixi": "8.00",
//        "again_shouxufei": "26.70"
//        },
//        {
//        "again_term": "56",
//        "again_pack": "1",
//        "again_bid_id": "167",
//        "again_fangkuan": "1483518591",
//        "plan_id": "10208",
//        "again_time": "1483518483",
//        "again_fuwu": "22.00",
//        "id": "19",
//        "loan_user_id": "11947",
//        "again_flag": "2",
//        "loan_id": "12058",
//        "again_money": "266.00",
//        "again_lixi": "8.00",
//        "again_shouxufei": "26.60"
//        }
//        ],
//        "loan_status": "6",
//        "loan_service": "0.00"
//        },
//        "message": "查询成功",
//        "result": 1
//        }

