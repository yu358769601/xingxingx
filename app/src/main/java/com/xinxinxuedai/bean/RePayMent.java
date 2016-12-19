package com.xinxinxuedai.bean;

import java.util.List;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 16:51 . 2016年12月16日
 * 描述:查询还款记录
 * <p>
 * <p>
 * 备注:
 */

public class RePayMent {
//
//    hashtable.put("action","getLoanPlanList");
//    LogUtils.i("人员ID"+Share.getToken(context));
//    hashtable.put("loan_id", Share.getToken(context));

    /**
     * result : 1
     * message : 查询成功
     * data : [{"id":"4177","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2016-11-25   16:00:00","real_data":"1479877897","current_flag":"1","repay_status":"1","real_money":"82.50","again_flag":"0","again_id":"0"},{"id":"4178","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2016-12-02   16:00:00","real_data":"1480563973","current_flag":"2","repay_status":"1","real_money":"82.50","again_flag":"0","again_id":"0"},{"id":"4179","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2016-12-09   16:00:00","real_data":"1480583458","current_flag":"3","repay_status":"1","real_money":"82.50","again_flag":"0","again_id":"0"},{"id":"4180","user_loan_id":"11440","money":"750.00","interest_money":"82.50","service_fee":"30.00","weiyue_money":"0.00","plan_date":"2016-12-16   16:00:00","real_data":"0","current_flag":"4","repay_status":"0","real_money":"0.00","again_flag":"1","again_id":"0"},{"id":"4181","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2016-12-23   16:00:00","real_data":"0","current_flag":"5","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"},{"id":"4182","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2016-12-30   16:00:00","real_data":"0","current_flag":"6","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"},{"id":"4183","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2017-01-06   16:00:00","real_data":"0","current_flag":"7","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"},{"id":"4184","user_loan_id":"11440","money":"750.00","interest_money":"82.50","service_fee":"30.00","weiyue_money":"0.00","plan_date":"2017-01-13   16:00:00","real_data":"0","current_flag":"8","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"},{"id":"4185","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2017-01-20   16:00:00","real_data":"0","current_flag":"9","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"},{"id":"4186","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2017-01-27   16:00:00","real_data":"0","current_flag":"10","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"},{"id":"4187","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2017-02-03   16:00:00","real_data":"0","current_flag":"11","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"},{"id":"4188","user_loan_id":"11440","money":"750.00","interest_money":"82.50","service_fee":"30.00","weiyue_money":"0.00","plan_date":"2017-02-10   16:00:00","real_data":"0","current_flag":"12","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"},{"id":"4189","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2017-02-17   16:00:00","real_data":"0","current_flag":"13","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"},{"id":"4190","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2017-02-24   16:00:00","real_data":"0","current_flag":"14","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"},{"id":"4191","user_loan_id":"11440","money":"0.00","interest_money":"82.50","service_fee":"0.00","weiyue_money":"0.00","plan_date":"2017-03-03   16:00:00","real_data":"0","current_flag":"15","repay_status":"0","real_money":"0.00","again_flag":"0","again_id":"0"}]
     */

    public int result;
    public String message;
    public List<DataBean> data;

    public static class DataBean {
        /**
         *
         * id : 4177
         *
         * 借款id
         * user_loan_id : 11440
         *
         * 还款本金
         * money : 0.00
         *
         * 还款服务费
         * interest_money : 82.50
         *
         *还款利息
         * service_fee : 0.00
         *
         *违约金
         * weiyue_money : 0.00
         *
         *计划还款时间
         * plan_date : 2016-11-25   16:00:00
         *
         *真实还款时间
         * real_data : 1479877897
         *
         *还款期数
         * current_flag : 1
         *
         *还款状态 0 待还款  1 已还款 2 逾期 3提前还款 4坏账5减免
         * repay_status : 1
         *
         * 真实还款金额
         * real_money : 82.50
         *
         *具备再分期条件0 不具备 1 具备
         * again_flag : 0
         *
         *再分期id
         * again_id : 0
         */

        public String id;
        public String user_loan_id;
        public String money;
        public String interest_money;
        public String service_fee;
        public String weiyue_money;
        public String plan_date;
        public String real_data;
        public String current_flag;
        public int repay_status;
        public String real_money;
        public int again_flag;
        public String again_id;
    }


}

//{
//        "result": 1,
//        "message": "查询成功",
//        "data": [
//        {
//        "id": "4177",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2016-11-25   16:00:00",
//        "real_data": "1479877897",
//        "current_flag": "1",
//        "repay_status": "1",
//        "real_money": "82.50",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4178",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2016-12-02   16:00:00",
//        "real_data": "1480563973",
//        "current_flag": "2",
//        "repay_status": "1",
//        "real_money": "82.50",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4179",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2016-12-09   16:00:00",
//        "real_data": "1480583458",
//        "current_flag": "3",
//        "repay_status": "1",
//        "real_money": "82.50",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4180",
//        "user_loan_id": "11440",
//        "money": "750.00",
//        "interest_money": "82.50",
//        "service_fee": "30.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2016-12-16   16:00:00",
//        "real_data": "0",
//        "current_flag": "4",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "1",
//        "again_id": "0"
//        },
//        {
//        "id": "4181",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2016-12-23   16:00:00",
//        "real_data": "0",
//        "current_flag": "5",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4182",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2016-12-30   16:00:00",
//        "real_data": "0",
//        "current_flag": "6",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4183",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2017-01-06   16:00:00",
//        "real_data": "0",
//        "current_flag": "7",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4184",
//        "user_loan_id": "11440",
//        "money": "750.00",
//        "interest_money": "82.50",
//        "service_fee": "30.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2017-01-13   16:00:00",
//        "real_data": "0",
//        "current_flag": "8",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4185",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2017-01-20   16:00:00",
//        "real_data": "0",
//        "current_flag": "9",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4186",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2017-01-27   16:00:00",
//        "real_data": "0",
//        "current_flag": "10",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4187",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2017-02-03   16:00:00",
//        "real_data": "0",
//        "current_flag": "11",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4188",
//        "user_loan_id": "11440",
//        "money": "750.00",
//        "interest_money": "82.50",
//        "service_fee": "30.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2017-02-10   16:00:00",
//        "real_data": "0",
//        "current_flag": "12",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4189",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2017-02-17   16:00:00",
//        "real_data": "0",
//        "current_flag": "13",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4190",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2017-02-24   16:00:00",
//        "real_data": "0",
//        "current_flag": "14",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        },
//        {
//        "id": "4191",
//        "user_loan_id": "11440",
//        "money": "0.00",
//        "interest_money": "82.50",
//        "service_fee": "0.00",
//        "weiyue_money": "0.00",
//        "plan_date": "2017-03-03   16:00:00",
//        "real_data": "0",
//        "current_flag": "15",
//        "repay_status": "0",
//        "real_money": "0.00",
//        "again_flag": "0",
//        "again_id": "0"
//        }
//        ]
//        }
