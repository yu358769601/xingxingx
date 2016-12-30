package com.xinxinxuedai.MVP.Repayment_detailsActivity.presenter;

import android.content.Context;
import android.widget.LinearLayout;

import com.xinxinxuedai.MVP.Repayment_detailsActivity.contract.Repayment_detailsActivity_mvpContract;
import com.xinxinxuedai.MVP.Repayment_detailsActivity.model.Repayment_detailsActivity_mvpModel;

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
}
