package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.FuYouChongZhi;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 8:42 . 2016年12月13日
 * 描述:s
 * <p>
 * <p>
 * 备注:
 */

public class FuYouChongZhi_Request {

    private static HttpURLConnection mHttpURLConnection;

    public static HttpURLConnection request(final Context context, Hashtable<String, String> hashtable, final NetWorkCallBack<FuYouChongZhi> netWorkCallBack) {
        hashtable.put("action", "fuYouChongZhi");
        hashtable.put("loan_id", Share.getToken(context));

        LogUtils.i("富友充值获取订单"+hashtable);
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_借款人充值"+"正常内容"+jsonObject);
                                FuYouChongZhi fuYouChongZhi = jsonObject.toJavaObject(FuYouChongZhi.class);
                                netWorkCallBack.onSucceed(fuYouChongZhi,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, context.getString(R.string.tojsonerror) );
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_借款人充值"+"失败内容"+errorString);
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
