package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.GetInfoShow;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 11:40 . 2016年12月12日
 * 描述:网络请求_判断借款人信息是否为空(二级页面重要)
 * <p>
 * <p>
 * 备注:
 */

public class GetInfoShow_Request {

    private static GetInfoShow sData;
    private static HttpURLConnection mHttpURLConnection;

    public static HttpURLConnection request(final Context context, final NetWorkCallBack<GetInfoShow> netWorkCallBack) {
        if (null!=sData)
            netWorkCallBack.onSucceed(sData,NetWorkCallBack.CACHEDATA);
        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        hashtable.put("action", "getInfoShow");
        hashtable.put("loan_id", Share.getToken(context));
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_"+"判断借款人信息是否为空"+"正常内容"+jsonObject);
                                sData = jsonObject.getObject("data", GetInfoShow.class);
                                netWorkCallBack.onSucceed(sData,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_"+"判断借款人信息是否为空"+"失败内容"+errorString);
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
