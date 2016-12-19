package com.xinxinxuedai.bean;

/**
 * Created by 35876 于萌萌
 * 创建日期: 14:10 . 2016年12月06日
 * 描述:借款状态页面的 获取到的数据
 * <p>
 * <p>
 * 备注:
 */

public class ApplicationStatusData {
    public String s1;
    public String s2;
    public String s3;
    public String s4;
    public String s5;


    public ApplicationStatusData(String s1, String s2, String s3, String s4, String s5) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
    }

    @Override
    public String toString() {
        return "ApplicationStatusData{" +
                "s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                ", s3='" + s3 + '\'' +
                ", s4='" + s4 + '\'' +
                ", s5='" + s5 + '\'' +
                '}';
    }
}
