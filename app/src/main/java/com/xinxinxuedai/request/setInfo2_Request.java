package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.SetInfo2;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

import static com.xinxinxuedai.request.RepaymentListRequest.mHttpURLConnection;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:50 . 2016年12月12日
 * 描述:网络请求_添加個人信息2
 * <p>
 * <p>
 * 备注:
 */

public class setInfo2_Request {

    private static SetInfo2 sInfo2;

    public static HttpURLConnection request(final Context context, Hashtable<String,String> hashtable , final NetWorkCallBack<SetInfo2> netWorkCallBack) {
        if (null!=sInfo2)
            netWorkCallBack.onSucceed(sInfo2);
        //入口
        hashtable.put("action", "setInfo2");
        //身份认证
        hashtable.put("loan_id", Share.getToken(context));
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_"+"添加用户借款信息2"+"正常内容"+jsonObject);
                                sInfo2 = jsonObject.toJavaObject(SetInfo2.class);
                                netWorkCallBack.onSucceed(sInfo2);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_"+"添加用户借款信息2"+"失败内容"+errorString);
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
