package com.xinxinxuedai.db.bean;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 10:56 . 2016年12月23日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class JsonAll {
    String name;
    String msg;

    public JsonAll() {
    }

    public JsonAll(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonAll{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
