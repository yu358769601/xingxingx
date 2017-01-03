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
import com.xinxinxuedai.bean.RePayMentend;
import com.xinxinxuedai.bean.RepaymentList;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.Repayment_Request;

import java.util.ArrayList;
import java.util.Hashtable;

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
    private ArrayList<RadioGroupData> mList;

    public Repayment_detailsActivity_mvpModel(Context context) {
        this.context = context;
    }

    public  ArrayList<RadioGroupData> getList(){
        return mList;
    }
    @Override
    public void setData(LinearLayout repayment_details_rg, final Repayment_detailsActivity_mvpContract.GetModelCallBack callBack) {

        mList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Double aDouble = new Double(i);
            mList.add(new RadioGroupData(aDouble,"无视"+i,0));
        }

        UtilsCheck utilsCheck = new UtilsCheck();
        utilsCheck.setCheckBoxsData(context,repayment_details_rg, mList,interest_money ,new ItemNumCallBack<RadioGroupData>() {
            @Override
            public void getNum(int postion, RadioGroupData data, double checkSum) {
                LogUtils.i("点了多少"+postion +"内容是"+data);


                callBack.getmoney(checkSum);
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

    @Override
    public void sub(int postion, RepaymentList.DataBean dataList, final Repayment_detailsActivity_mvpContract.CallBackMsg callBack) {
        if (null==dataList){
            return;
        }
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).tag==1){
                LogUtils.i("代金券有"+mList.get(i));
            }
        }

        int id = dataList.id;
        String user_loan_id = dataList.user_loan_id;
        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        hashtable.put("id",id+"");
        hashtable.put("loan_id",user_loan_id);
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

    @Override
    public void setMoney(double interest_money) {
        this.interest_money = interest_money;
    }


}
