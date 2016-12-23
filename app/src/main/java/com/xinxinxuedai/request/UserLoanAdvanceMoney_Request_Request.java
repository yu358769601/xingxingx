package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.UserLoanAdvanceMoney;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 8:42 . 2016年12月13日
 * 描述:提前还款_网络请求
 * <p>
 * <p>
 * 备注:
 */

public class UserLoanAdvanceMoney_Request_Request {

    private static HttpURLConnection mHttpURLConnection;

    public static HttpURLConnection request(final Context context, Hashtable<String, String> hashtable, final NetWorkCallBack<UserLoanAdvanceMoney> netWorkCallBack) {
        hashtable.put("action", "userLoanAdvanceMoney");
        //这个地方必须是这个 之前接口问题
        hashtable.put("user_id", Share.getToken(context));
        LogUtils.i("提前还款id"+Share.getToken(context));

        LogUtils.i("传过去的数据有"+hashtable.toString());
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_提前还款"+"正常内容"+jsonObject);
                                UserLoanAdvanceMoney userLoanAdvanceMoney = jsonObject.toJavaObject(UserLoanAdvanceMoney.class);
                                netWorkCallBack.onSucceed(userLoanAdvanceMoney,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_提前还款"+"失败内容"+errorString);
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
