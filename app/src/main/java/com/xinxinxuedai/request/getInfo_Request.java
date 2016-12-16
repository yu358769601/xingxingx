package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;


/**
 * Created by Administrator 于萌萌
 * 创建日期: 11:40 . 2016年12月12日
 * 描述:网络请求_获取借贷人信息
 * <p>
 * <p>
 * 备注:
 */

public class GetInfo_Request {

    private static GetInfo sData;
    private static HttpURLConnection mHttpURLConnection;

    public static HttpURLConnection request(final Context context, final NetWorkCallBack<GetInfo> netWorkCallBack) {
        if (null!=sData)
        netWorkCallBack.onSucceed(sData,NetWorkCallBack.CACHEDATA);
        LogUtils.i("访问网络_缓存"+sData);
        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        hashtable.put("action", "getInfo");
        hashtable.put("loan_id", Share.getToken(context));
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_"+"获取借贷人信息"+"正常内容"+jsonObject);
                                sData = jsonObject.getObject("data", GetInfo.class);
                                LogUtils.i("访问网络_访问网络之后"+sData);
                                netWorkCallBack.onSucceed(sData,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_"+"获取借贷人信息"+"失败内容"+errorString);
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
