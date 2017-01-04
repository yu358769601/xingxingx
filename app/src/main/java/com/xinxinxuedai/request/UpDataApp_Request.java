package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.bean.UpDataApp;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 15:48 . 2017年01月03日
 * 描述:更新APP请求_网络请求
 * <p>
 * <p>
 * 备注:
 */

public class UpDataApp_Request {
    private static HttpURLConnection mHttpURLConnection;

    public static HttpURLConnection request(final Context context, Hashtable<String, String> hashtable, final NetWorkCallBack<UpDataApp> netWorkCallBack) {
        hashtable.put("action", "getVersionCode");
        //hashtable.put("user_id", Share.getToken(context));

        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_更新APP"+"正常内容"+jsonObject);

                                UpDataApp dataApp = jsonObject.toJavaObject(UpDataApp.class);
                               netWorkCallBack.onSucceed(dataApp,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, context.getString(R.string.tojsonerror) );
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_更新APP"+"失败内容"+errorString);
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
