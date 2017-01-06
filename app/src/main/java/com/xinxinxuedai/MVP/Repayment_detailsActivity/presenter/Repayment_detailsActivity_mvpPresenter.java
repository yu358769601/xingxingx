package com.xinxinxuedai.MVP.Repayment_detailsActivity.presenter;

import android.content.Context;
import android.widget.LinearLayout;

import com.xinxinxuedai.MVP.Repayment_detailsActivity.contract.Repayment_detailsActivity_mvpContract;
import com.xinxinxuedai.MVP.Repayment_detailsActivity.model.Repayment_detailsActivity_mvpModel;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.bean.RepaymentList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:03 . 2016年12月30日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public class Repayment_detailsActivity_mvpPresenter implements Repayment_detailsActivity_mvpContract.Presenter {
    //逻辑模型
    Repayment_detailsActivity_mvpContract.Model mModel;

    //返回给activity
    Repayment_detailsActivity_mvpContract.View mView;

    public  Repayment_detailsActivity_mvpPresenter(Context context,Repayment_detailsActivity_mvpContract.View mView){
        mModel =new Repayment_detailsActivity_mvpModel(context);
        this.mView =mView;
    }

    /**
     * 获取网络 数据的方法
     * @param repayment_details_rg
     */
    public void getNetData(LinearLayout repayment_details_rg) {
        //获取了网络数据
        mModel.setData(repayment_details_rg, new Repayment_detailsActivity_mvpContract.GetModelCallBack() {
            @Override
            public void getmoney(double v) {
                mView.CallBack(v);
            }
        });

    }

    /**
     * 点了还款按钮之后
     * @param again_flag
     * @param positon
     * @param dataList
     */
    public void subHuanKuan(int again_flag, int positon, RepaymentList.DataBean dataList) {
       // ArrayList<RadioGroupData> list = mModel.getList();
        mModel.sub(positon,dataList, again_flag,new Repayment_detailsActivity_mvpContract.CallBackMsg() {


            @Override
            public void chenggong(String s) {
                LogUtils.i("现在还款成功的内容是"+s);

                mView.closeActivity();
            }

            @Override
            public void shibai(String s) {
                LogUtils.i("现在还款失败的内容是"+s);
            }
        });
    }

    /**
     * 设置给逻辑  告诉他 这个数值  不能低于他
     * @param interest_money
     */
    public void setMoney(double interest_money) {
        mModel.setMoney(interest_money);
    }
}
