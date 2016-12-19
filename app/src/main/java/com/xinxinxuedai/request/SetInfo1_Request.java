package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.bean.SetInfo1;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;


/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:50 . 2016年12月12日
 * 描述:网络请求_添加個人信息1
 * <p>s
 * <p>
 * 备注:
 */

public class SetInfo1_Request {

    private static SetInfo1 sInfo1;
    private static HttpURLConnection mHttpURLConnection;

    public static HttpURLConnection request(final Context context, Hashtable<String,String> hashtable , final NetWorkCallBack<SetInfo1> netWorkCallBack) {
        if (null!=sInfo1)
            netWorkCallBack.onSucceed(sInfo1,NetWorkCallBack.CACHEDATA);
        hashtable.put("action", "setInfo1");
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_"+"添加用户借款信息1"+"正常内容"+jsonObject);
                                sInfo1 = jsonObject.toJavaObject(SetInfo1.class);
                                netWorkCallBack.onSucceed(sInfo1,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_"+"添加用户借款信息1"+"失败内容"+errorString);
                        netWorkCallBack.onError(errorString.getString("message"));
                    }

                    @Override
                    public void onBackHttpURLConnection(HttpURLConnection httpURLConnection) {
                        mHttpURLConnection =httpURLConnection;

                    }
                });
        return mHttpURLConnection;
    }
}
