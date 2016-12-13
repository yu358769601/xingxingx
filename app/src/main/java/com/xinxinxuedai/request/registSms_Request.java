package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.bean.RegistSms;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

import static com.xinxinxuedai.request.RepaymentListRequest.mHttpURLConnection;

/**
 * Created by 35876 于萌萌
 * 创建日期: 13:01 . 2016年12月04日
 * 描述:网络请求_发送验证码
 * <p>
 * <p>
 * 备注:
 */

public class registSms_Request {
    public static HttpURLConnection request(final Context context, Hashtable<String, String> hashtable, final NetWorkCallBack<RegistSms> netWorkCallBack) {
        hashtable.put("action", "registSms");
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_验证码"+"正常内容"+jsonObject);
                               // RegistSms
                                RegistSms RegistSms = jsonObject.toJavaObject(RegistSms.class);
                                netWorkCallBack.onSucceed(RegistSms);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_验证码"+"失败内容"+errorString);
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
