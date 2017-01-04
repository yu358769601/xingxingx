package com.xinxinxuedai.MVP.Repayment_detailsActivity.model;

import android.content.Context;
import android.widget.LinearLayout;

import com.xinxinxuedai.MVP.Repayment_detailsActivity.contract.Repayment_detailsActivity_mvpContract;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsCheBoxs.ItemNumCallBack;
import com.xinxinxuedai.Utils.UtilsCheBoxs.UtilsCheck;
import com.xinxinxuedai.Utils.UtilsCheBoxs.bean.RadioGroupData;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.bean.Daijinquan;
import com.xinxinxuedai.bean.RePayMentend;
import com.xinxinxuedai.bean.RepaymentList;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.RepaymentZFQ_Request;
import com.xinxinxuedai.request.Repayment_Request;
import com.xinxinxuedai.request.daijinquan_Request;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:03 . 2016年12月30日
 * 描述:还款详情网络逻辑
 * <p>
 * <p>
 * 备注:
 */
public class Repayment_detailsActivity_mvpModel implements Repayment_detailsActivity_mvpContract.Model {
    int oldPostion = -1;
    Context context;

    double interest_money;
    private ArrayList<RadioGroupData> mList = new ArrayList<>();
    private List<Daijinquan.DataBean> mData;
    private ArrayList<String> mStrings;

    public Repayment_detailsActivity_mvpModel(Context context) {
        this.context = context;
    }

    public  ArrayList<RadioGroupData> getList(){
        return mList;
    }
    @Override
    public void setData(final LinearLayout repayment_details_rg, final Repayment_detailsActivity_mvpContract.GetModelCallBack callBack) {
        //获取代金券
        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        daijinquan_Request.request(context, hashtable, new NetWorkCallBack<Daijinquan>() {
            @Override
            public void onSucceed(Daijinquan daijinquan, int dataMode) {
                if (daijinquan.result==1&&daijinquan.data.size()>0){
                    mData = daijinquan.data;
                    initDaijinquanList(mData,repayment_details_rg,callBack);
                }
            }

            @Override
            public void onError(String jsonObject) {

            }
        });



//        UtilsRadio utilsRadio = new UtilsRadio();
//        utilsRadio.setRadioGroupData(context, repayment_details_rg, list, new ItemNumCallBack<RadioGroupData>() {
//            @Override
//            public void getNum(int postion, RadioGroupData data) {
//                if (oldPostion!=postion){
//                    oldPostion= postion;
//                    LogUtils.i("点了多少"+postion +"内容是"+data);
//                    callBack.getmoney(data.money);
//                }
//
//            }
//        });
    }

    /**
     * 初始化 代金券列表
     * @param data
     * @param repayment_details_rg
     * @param callBack
     */
    private void initDaijinquanList(List<Daijinquan.DataBean> data, LinearLayout repayment_details_rg, final Repayment_detailsActivity_mvpContract.GetModelCallBack callBack) {
        for (int i = 0; i < data.size(); i++) {
            Daijinquan.DataBean dataBean = data.get(i);

            RadioGroupData radioGroupData = new RadioGroupData();

            radioGroupData.setChoujiang_time(dataBean.choujiang_time);
            radioGroupData.setFlag(dataBean.flag);
            radioGroupData.setGuoqi_time(dataBean.guoqi_time);
            radioGroupData.setGuoqi_time1(dataBean.guoqi_time1);
            radioGroupData.setId(dataBean.id);
            radioGroupData.setLoan_id(dataBean.loan_id);
            radioGroupData.setMoney(dataBean.money);
            radioGroupData.setPlan_id(dataBean.plan_id);
            radioGroupData.setShiyong_time(dataBean.shiyong_time);
            radioGroupData.setItemFlag(0);
            mList.add(radioGroupData);
        }

        //代金券测试
//        for (int i = 0; i < 100; i++) {
//            Double aDouble = new Double(i);
////            String format = String.format("%.2f", i);
////            double v = Double.parseDouble(format);
//            RadioGroupData radioGroupData = new RadioGroupData();
//
//            radioGroupData.setChoujiang_time(i+"");
//            radioGroupData.setFlag(0);
//            radioGroupData.setGuoqi_time("0");
//            radioGroupData.setGuoqi_time1("0");
//            radioGroupData.setId(i+"");
//            radioGroupData.setLoan_id(i+"");
//            radioGroupData.setMoney(aDouble);
//            radioGroupData.setPlan_id(i+"");
//            radioGroupData.setShiyong_time(i+"");
//            radioGroupData.setItemFlag(0);
//            mList.add(radioGroupData);
//        }

        UtilsCheck utilsCheck = new UtilsCheck();
        utilsCheck.setCheckBoxsData(context,repayment_details_rg, mList,interest_money ,new ItemNumCallBack<RadioGroupData>() {
            @Override
            public void getNum(int postion, RadioGroupData data, double checkSum) {
                LogUtils.i("点了多少"+postion +"内容是"+data);


                callBack.getmoney(checkSum);
            }
        });
    }



    @Override
    public void sub(int postion, RepaymentList.DataBean dataList, final Repayment_detailsActivity_mvpContract.CallBackMsg callBack) {
        if (null==dataList){
            return;
        }
        //便利代金券集合
        if (mList.size()>0){
            mStrings = new ArrayList<>();
            for (int i = 0; i < mList.size(); i++) {
                //如果有人用了 代金券
                if (mList.get(i).getItemFlag()==1){
                    LogUtils.i("代金券用的有"+mList.get(i));
                    mStrings.add(mList.get(i).getId());
                }
            }
        }
        String s = getString(mStrings);
        //两种还款
        //如果再分期  就用再分期为后缀的  如果 不是再分期 就用这个

        int id = dataList.id;
        String user_loan_id = dataList.user_loan_id;
        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        hashtable.put("id",id+"");
        hashtable.put("loan_id",user_loan_id);
        hashtable.put("daijinjuan",s);
        if (dataList.again_flag==0){
            request(context, hashtable,callBack);
        }else if (dataList.again_flag==1){
            requestZFQ(context, hashtable,callBack);
        }

    }

    private void requestZFQ(final Context context, Hashtable<String, String> hashtable, final Repayment_detailsActivity_mvpContract.CallBackMsg callBack) {
        RepaymentZFQ_Request.request(context, hashtable, new NetWorkCallBack<RePayMentend>() {
            @Override
            public void onSucceed(RePayMentend rePayMentend, int dataMode) {
                UtilsToast.showToast(context, rePayMentend.message);
                LogUtils.i("还款网络成功回来的数据"+ rePayMentend.message);
                callBack.chenggong(rePayMentend.message);
            }

            @Override
            public void onError(String jsonObject) {
                UtilsToast.showToast(context, jsonObject);
                callBack.shibai(jsonObject);
            }
        });
    }

    private void request(final Context context, Hashtable<String, String> hashtable, final Repayment_detailsActivity_mvpContract.CallBackMsg callBack) {
        Repayment_Request.request(context, hashtable, new NetWorkCallBack<RePayMentend>() {
            @Override
            public void onSucceed(RePayMentend rePayMent1, int dataMode) {
                UtilsToast.showToast(context, rePayMent1.message);
                LogUtils.i("还款网络成功回来的数据"+ rePayMent1.message);
                callBack.chenggong(rePayMent1.message);
            }

            @Override
            public void onError(String jsonObject) {
                UtilsToast.showToast(context, jsonObject);
                callBack.shibai(jsonObject);
            }
        });
    }

    /**
     * 获取字符串拼接的 字符串
     * @param strings
     * @return
     */
    private String getString(ArrayList<String> strings) {
        if (null==strings||strings.size()==0){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            builder.append(strings.get(i));
            if (i!=strings.size()-1){
                builder.append("^");
            }
        }

        return builder.toString();
    }

    @Override
    public void setMoney(double interest_money) {
        this.interest_money = interest_money;
    }


}
