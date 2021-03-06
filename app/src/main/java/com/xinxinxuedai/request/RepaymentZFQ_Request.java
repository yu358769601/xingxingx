package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.RePayMentend;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:50 . 2016年12月12日
 * 描述:网络请求_还款
 * <p>s
 * <p>
 * 备注:
 */

public class RepaymentZFQ_Request {

    private static HttpURLConnection mHttpURLConnection;

    public static HttpURLConnection request(final Context context, Hashtable<String,String> hashtable , final NetWorkCallBack<RePayMentend> netWorkCallBack) {
        //入口
        hashtable.put("action", "repaymentZFQ");
        //身份认证
        hashtable.put("user_id", Share.getToken(context));

        LogUtils.i("还款发出去的数据"+hashtable);
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL,  new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_"+"还款"+"正常内容"+jsonObject);
                                RePayMentend rePayMent1 = jsonObject.toJavaObject(RePayMentend.class);
                                netWorkCallBack.onSucceed(rePayMent1,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, context.getString(R.string.tojsonerror) );
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_"+"还款"+"失败内容"+errorString);
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
