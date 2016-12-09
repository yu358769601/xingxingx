package com.xinxinxuedai.request;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.SQUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.UtilsNet.NetAesCallBack;
import com.xinxinxuedai.UtilsNet.NetMessage;
import com.xinxinxuedai.util.Constants;

import java.net.HttpURLConnection;
import java.util.Hashtable;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 10:25 . 2016年12月09日
 * 描述:还款列表_网络请求 还款列表加载更多_网络请求
 * <p>
 * <p>
 * 备注:
 */

public class RepaymentListRequest {
    static HttpURLConnection mHttpURLConnection;
    public static int pn = 1;
    //第一次请求
    public static HttpURLConnection request(final Context context, final NetAesCallBack netAesCallBack) {
            pn = 1;
            Hashtable<String, String> hashtable = new Hashtable<String, String>();
            hashtable.put("action", "getPassengerOverOrder");
            LogUtils.i("人员ID"+ SQUtils.getId(context)+"");
            hashtable.put("passenger_id", SQUtils.getId(context));
            hashtable.put("pageNo", pn+"");


            NetMessage.get(context)
                    .sendMessage(Constants.urlList, null, Constants.TEST, new NetAesCallBack() {
                        @Override
                        public void onSucceed(JSONObject jsonObject) {
                            try {
                                LogUtils.i("获取网络数据" + jsonObject);
                                //Carbean carbean = JSONArray.toJavaObject(jsonObject, Carbean.class);
                                if (null != jsonObject) {
                                    //addMarkerData(carbean);
                                    netAesCallBack.onSucceed(jsonObject);
                                }
                            } catch (Exception e) {
                                UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(String errorString) {
                            LogUtils.i("获取订单列表的网络请求json数据是啥" + errorString);
                            netAesCallBack.onError(errorString);
                        }

                        @Override
                        public void onBackHttpURLConnection(HttpURLConnection httpURLConnection) {
                            mHttpURLConnection =httpURLConnection;

                        }
                    });
        return mHttpURLConnection;
        }
    //加载更多请求
    public static HttpURLConnection requestmore(final Context context, final NetAesCallBack netAesCallBack) {
            pn ++;

            Hashtable<String, String> hashtable = new Hashtable<String, String>();
            hashtable.put("action", "getPassengerOverOrder");
            hashtable.put("passenger_id", SQUtils.getId(context));
            LogUtils.i("pn是多少"+pn);
            hashtable.put("pageNo", pn+"");


            NetMessage.get(context)
                    .sendMessage(Constants.urlList, null, Constants.TEST, new NetAesCallBack() {
                        @Override
                        public void onSucceed(JSONObject jsonObject) {
                            try {
                                LogUtils.i("json数据是啥加载更多" + jsonObject);
                                //Carbean carbean = JSONArray.toJavaObject(jsonObject, Carbean.class);
                                if (null != jsonObject) {
                                    //addMarkerData(carbean);
                                    netAesCallBack.onSucceed(jsonObject);
                                }
                            } catch (Exception e) {
                                UtilsToast.showToast(context, "json解析出错" + jsonObject.toString());
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(String errorString) {
                            LogUtils.i("json数据是啥加载更多" + errorString);
                            netAesCallBack.onError(errorString);
                        }

                        @Override
                        public void onBackHttpURLConnection(HttpURLConnection httpURLConnection) {
                            mHttpURLConnection = httpURLConnection;
                        }
                    });
        return mHttpURLConnection;
        }
    }
