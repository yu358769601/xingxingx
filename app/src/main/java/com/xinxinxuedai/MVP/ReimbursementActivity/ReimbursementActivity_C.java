package com.xinxinxuedai.MVP.ReimbursementActivity;

import android.content.Intent;

import com.xinxinxuedai.MVP.baseMVP.BaseMVPinterFace_CallBack;
import com.xinxinxuedai.bean.RepaymentList;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:27 . 2016年12月07日
 * 描述:我要还款_C
 * <p>
 * <p>
 * 备注:
 */

public interface ReimbursementActivity_C extends BaseMVPinterFace_CallBack{
    /**
     * 获取网络请求
     * @param httpURLConnection
     */
    void getNetRequest(HttpURLConnection httpURLConnection);


    void getShowDialog1(int positon, List<RepaymentList.DataBean> data, int again_flag);
    void getShowDialog2(int positon, List<RepaymentList.DataBean> data);
    void getShowDialog3(double positon);


    void start(Intent intent);
}
