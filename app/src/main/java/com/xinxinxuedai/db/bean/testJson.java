package com.xinxinxuedai.db.bean;

/**
 * Created by 35876 于萌萌
 * 创建日期: 18:50 . 2016年12月25日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class TestJson {

    public String name;
    public String msg;

    public TestJson(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "TestJson{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
