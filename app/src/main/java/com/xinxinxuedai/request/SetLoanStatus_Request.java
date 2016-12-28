package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.SetLoanStatus;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:38 . 2016年12月12日
 * 描述:上传图片 和视频 确认提交
 * <p>s
 * <p>
 * 备注:
 */

public class SetLoanStatus_Request {


    private static HttpURLConnection mHttpURLConnection;

    public static HttpURLConnection request(final Context context, final NetWorkCallBack<SetLoanStatus> netWorkCallBack) {
        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        //入口
        hashtable.put("action", "setLoanStatus");
        //token
        hashtable.put("loan_id", Share.getToken(context));
        hashtable.put("loan_from", "android");
        NetMessage.get(context)
                .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                    @Override
                    public void onSucceed(JSONObject jsonObject) {
                        try {
                            if (null != jsonObject) {
                                LogUtils.i("网络请求_"+"上传图片 和视频 确认提交"+"正常内容"+jsonObject);
                                SetLoanStatus data = jsonObject.toJavaObject(SetLoanStatus.class);
                                netWorkCallBack.onSucceed(data,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, context.getString(R.string.tojsonerror) );
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(JSONObject errorString) {
                        LogUtils.i("网络请求_"+"上传图片 和视频 确认提交"+"失败内容"+errorString);
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
