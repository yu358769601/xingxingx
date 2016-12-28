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
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.db.DAO.MyDbDAO;
import com.xinxinxuedai.db.DAO.dbCallBackHelper;
import com.xinxinxuedai.db.Table;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;


/**
 * Created by Administrator 于萌萌
 * 创建日期: 11:40 . 2016年12月12日
 * 描述:网络请求_获取借贷人信息
 * <p>_回显
 * <p>
 * 备注:
 */

public class GetInfo_Request {

    private static HttpURLConnection mHttpURLConnection;

    public static HttpURLConnection request(final Context context, final NetWorkCallBack<GetInfo> netWorkCallBack) {
        new MyDbDAO(context, GetInfo.class).find(Table.MyJson,"GetInfo",new dbCallBackHelper<GetInfo>(){
            /**
             * 获取数据库信息成功
             *
             * @param getInfo
             */
            @Override
            public void getDataSuccess(GetInfo getInfo) {
                LogUtils.i("我拿到数据了"+getInfo);
                netWorkCallBack.onSucceed(getInfo,NetWorkCallBack.CACHEDATA);
            }

            /**
             * 获取数据库信息失败
             *
             * @param error
             */
            @Override
            public void getDataError(String error) {
                LogUtils.i("我拿到数据了"+error);
            }
   });

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
                                GetInfo data = jsonObject.getObject("data", GetInfo.class);
                                new MyDbDAO(context, GetInfo.class).add_or_upData(Table.MyJson,"GetInfo",data);
                                LogUtils.i("访问网络_访问网络之后"+data);
                                netWorkCallBack.onSucceed(data,NetWorkCallBack.NETDATA);
                            }
                        } catch (Exception e) {
                            UtilsToast.showToast(context, context.getString(R.string.tojsonerror) );
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
