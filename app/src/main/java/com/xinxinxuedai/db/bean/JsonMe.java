package com.xinxinxuedai.db.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 9:32 . 2016年12月23日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class JsonMe {
    String name;
    JSONObject msg;

    public JsonMe() {
    }

    public JsonMe(String name, JSONObject msg) {
        this.name = name;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject getMsg() {
        return msg;
    }

    public void setMsg(JSONObject msg) {
        this.msg = msg;
    }
}
