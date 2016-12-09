package com.xinxinxuedai.UtilsNet;

import com.alibaba.fastjson.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by 35876 于萌萌
 * 创建日期: 9:39 . 2016年11月08日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface NetAesCallBack {
    void onSucceed(JSONObject jsonObject);
    void onError(String errorString);
    void onBackHttpURLConnection(HttpURLConnection httpURLConnection);
}
