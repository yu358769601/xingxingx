package com.xinxinxuedai.bean;

import java.util.List;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 11:11 . 2017年01月04日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class Daijinquan {
//    hashtable.put("action", "getDaijinquan");
//    hashtable.put("user_id", Share.getToken(context));


    /**
     * data : [{"id":"37","choujiang_time":"1483500208","shiyong_time":"0","money":"2.00","loan_id":"11950","plan_id":"0","guoqi_time1":"2017-01-18   11:23:28","guoqi_time":"1484709808","flag":"0"}]
     * message : 用户没有代金券
     * result : 1
     */

    public String message;
    public int result;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * 代金券的id
         * id : 37
         *
         * 获取代金券的时间
         * choujiang_time : 1483500208
         *
         * 啥时候使用的
         * shiyong_time : 0
         *
         * 面值
         * money : 2.00
         *
         * 谁的
         * loan_id : 11950
         *
         * 那天还款计划用的id
         * plan_id : 0
         *
         * 过期时间
         * guoqi_time1 : 2017-01-18   11:23:28
         *
         * 过期时间
         * guoqi_time : 1484709808
         *
         * 0 未使用  1 是已使用
         * flag : 0
         */

        public String id;
        public String choujiang_time;
        public String shiyong_time;
        public double money;
        public String loan_id;
        public String plan_id;
        public String guoqi_time1;
        public String guoqi_time;
        public int flag;
    }
}
//{"data":"","message":"用户没有代金券","result":0}

//{
//        "data": [{
//        "id": "37",
//        "choujiang_time": "1483500208",
//        "shiyong_time": "0",
//        "money": "2.00",
//        "loan_id": "11950",
//        "plan_id": "0",
//        "guoqi_time1": "2017-01-18   11:23:28",
//        "guoqi_time": "1484709808",
//        "flag": "0"
//        }],
//        "message": "用户没有代金券",
//        "result": 1
//        }