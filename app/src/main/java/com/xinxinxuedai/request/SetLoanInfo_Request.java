package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.bean.SetLoanInfo;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 12:38 . 2016年12月12日
 * 描述:网络请求_添加用户借款信息
 * <p>s
 * <p>
 * 备注:
 */

public class SetLoanInfo_Request {

    private static HttpURLConnection mHttpURLConnection;
    private static SetLoanInfo sSetLoanInfo;

    public static HttpURLConnection request(final Context context, Hashtable<String,String> hashtable , final NetWorkCallBack<SetLoanInfo> netWorkCallBack) {

        //入口
        hashtable.put("action", "setLoanInfo");
//        //token
//        hashtable.put("loan_id", Share.getToken(context));
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_"+"添加用户借款信息"+"正常内容"+jsonObject);
                                sSetLoanInfo = jsonObject.toJavaObject(SetLoanInfo.class);
                                netWorkCallBack.onSucceed(sSetLoanInfo,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, context.getString(R.string.tojsonerror) );
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_"+"添加用户借款信息"+"失败内容"+errorString);
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
