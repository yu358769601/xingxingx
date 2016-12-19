package com.xinxinxuedai.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 9:10 . 2016年12月16日
 * 描述: 获取二级页面的 信息是否完整的状态(重要的接口)
 * <p>edd
 * <p>
 * 备注:
 */

public class GetInfoShow {

        /**有拆分 data
         * 个人信息 (住址 父母名字 电话等)
         * info1 : 1
         * //银行卡信息
         * info3 : 1
         * 学校信息
         * info2 : 1
         */

        public int info1;
        public int info3;
        public int info2;
}

//{
//        "message": "查询成功",
//        "result": 1,
//        "data": {
//        "info1": 1,
//        "info3": 1,
//        "info2": 1
//        }
//        }
