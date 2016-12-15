package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.SetInfo3;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

import static com.xinxinxuedai.request.RepaymentListRequest.mHttpURLConnection;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:50 . 2016年12月12日
 * 描述:网络请求_添加個人信息3
 * <p>
 * <p>
 * 备注:
 */

public class setInfo3_Request {

    private static SetInfo3 sSetInfo3;

    public static HttpURLConnection request(final Context context, Hashtable<String,String> hashtable , final NetWorkCallBack<SetInfo3> netWorkCallBack) {
        if (null!=sSetInfo3)
            netWorkCallBack.onSucceed(sSetInfo3,NetWorkCallBack.CACHEDATA);
        hashtable.put("action", "setInfo3");
        hashtable.put("loan_id", Share.getToken(context));
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_"+"添加用户银行卡3"+"正常内容"+jsonObject);
                                sSetInfo3 = jsonObject.toJavaObject(SetInfo3.class);

                                netWorkCallBack.onSucceed(sSetInfo3,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_"+"添加用户银行卡3"+"失败内容"+errorString);
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
