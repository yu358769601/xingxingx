package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.RePayMent;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 10:25 . 2016年12月09日
 * 描述: 网络请求_还款列表
 *      网络请求_还款列表加载更多
 * <p>s
 * <p>
 * 备注:
 */

public class RepaymentListRequest {
    static HttpURLConnection mHttpURLConnection;
    public static int pn = 1;
    private static RePayMent sPayMent;

    //第一次请求
    public static HttpURLConnection request(final Context context, final NetWorkCallBack<RePayMent> netWorkCallBack) {
//        if (null!=sPayMent)
//            netWorkCallBack.onSucceed(sPayMent,NetWorkCallBack.CACHEDATA);
        // pn = 1;
            Hashtable<String, String> hashtable = new Hashtable<String, String>();
            hashtable.put("action","getLoanPlanList");
            LogUtils.i("人员ID"+Share.getToken(context));
            hashtable.put("loan_id", Share.getToken(context));

        //hashtable.put("pageNo", pn+"");


            NetMessage.get(context)
                    .sendMessage(Constants.new_url, hashtable, Constants.NORMAL, new NetAesCallBack() {
                        @Override
                        public void onSucceed(JSONObject jsonObject) {
                            try {
                                LogUtils.i("网络请求_还款记录"+"正常内容" + jsonObject);
                                if (null != jsonObject) {
                                    sPayMent = jsonObject.toJavaObject(RePayMent.class);
                                    netWorkCallBack.onSucceed(sPayMent,NetWorkCallBack.NETDATA);
                                }
                            } catch (Exception e) {
                                UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(JSONObject errorString) {
                            LogUtils.i("网络请求_还款记录"+"失败" + errorString);
                            netWorkCallBack.onError(errorString.getString("message"));
                        }

                        @Override
                        public void onBackHttpURLConnection(HttpURLConnection httpURLConnection) {
                            mHttpURLConnection =httpURLConnection;

                        }
                    });
        return mHttpURLConnection;
        }
    //加载更多请求
    public static HttpURLConnection requestmore(final Context context, final NetWorkCallBack netWorkCallBack) {
           // pn ++;

            Hashtable<String, String> hashtable = new Hashtable<String, String>();
         //   hashtable.put("action", "getPassengerOverOrder");
           // hashtable.put("passenger_id", SQUtils.getId(context));
            //LogUtils.i("pn是多少"+pn);
           // hashtable.put("pageNo", pn+"");


            NetMessage.get(context)
                    .sendMessage(Constants.urlList, null, Constants.TEST, new NetAesCallBack() {
                        @Override
                        public void onSucceed(JSONObject jsonObject) {
                            try {
                                LogUtils.i("json数据是啥加载更多" + jsonObject);
                                //Carbean carbean = JSONArray.toJavaObject(jsonObject, Carbean.class);
                                if (null != jsonObject) {
                                    //addMarkerData(carbean);
                                    netWorkCallBack.onSucceed(jsonObject,NetWorkCallBack.NETDATA);
                                }
                            } catch (Exception e) {
                                UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(JSONObject errorString) {
                            LogUtils.i("json数据是啥加载更多" + errorString);
                            netWorkCallBack.onError(errorString.getString("message"));
                        }

                        @Override
                        public void onBackHttpURLConnection(HttpURLConnection httpURLConnection) {
                            mHttpURLConnection = httpURLConnection;
                        }
                    });
        return mHttpURLConnection;
        }
    }
