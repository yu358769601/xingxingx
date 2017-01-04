package com.xinxinxuedai.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 13:22 . 2017年01月04日
 * 描述:更新App_bean
 * <p>
 * <p>
 * 备注:
 */

public class UpDataApp {
  //  hashtable.put("action", "getVersionCode");
    /**
     * data : {"qiangzhi":0,"msg":"更新了屌爆了的功能","banben":1,"wangzhi":"www.baidu.com"}
     * message : 成功
     * result : 1
     */

    public DataBean data;
    public String message;
    public int result;


    @Override
    public String toString() {
        return "UpDataApp{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    public static class DataBean {
        /** 是否强制更新
         * qiangzhi : 0
         * 更新内容
         * msg : 更新了屌爆了的功能
         * 版本号
         * banben : 1
         *
         * wangzhi : www.baidu.com
         */

        public int qiangzhi;
        public String msg;
        public int banben;
        public String wangzhi;

        @Override
        public String toString() {
            return "DataBean{" +
                    "qiangzhi=" + qiangzhi +
                    ", msg='" + msg + '\'' +
                    ", banben=" + banben +
                    ", wangzhi='" + wangzhi + '\'' +
                    '}';
        }
    }
}

//{
//        "data": {
//        "qiangzhi": 0,
//        "msg": "更新了屌爆了的功能",
//        "banben": 1,
//        "wangzhi": "www.baidu.com"
//        },
//        "message": "成功",
//        "result": 1
//        }
