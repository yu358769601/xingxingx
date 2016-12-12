package com.xinxinxuedai.request;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 9:20 . 2016年12月12日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface NetWorkCallBack {
    void onSucceed(JSONObject jsonObject);
    void onError(String jsonObject);
}
