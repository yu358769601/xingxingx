package com.xinxinxuedai.MVP.LoanProductsActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.bean.GetLoanDetail;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.getLoanDetail_Request;
import com.xinxinxuedai.ui.LoanApplicationActivity;

/**
 * Created by 35876 于萌萌
 * 创建日期: 15:48 . 2016年12月02日
 * 描述:LoanProductsActivity 逻辑处理
 * <p>
 * <p>
 * 备注:
 */

public class LoanProductsActivity_P extends BaseMvp<LoanProductsActivity_CallBack> implements LoanProductsActivity_method{

    static LoanProductsActivity_P mLoanProductsActivity_p;
    Context context;
    public LoanProductsActivity_P(Context context){
        this.context = context;
    }

    LoanProductsActivity_CallBack loanProductsActivity_callBack;
    @Override
    public void setCallBack(LoanProductsActivity_CallBack loanProductsActivity_callBack) {
        this.loanProductsActivity_callBack = loanProductsActivity_callBack;
    }



    public static LoanProductsActivity_P getLoanProductsActivity_P(Context context){
        if (null==mLoanProductsActivity_p){
            return mLoanProductsActivity_p = new LoanProductsActivity_P(context);

        }else{
            return mLoanProductsActivity_p;
        }
    }


    @Override
    public void onclicks(View view) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch ((int)view.getTag()){

                case 1:
                    intent.setClass(context, LoanApplicationActivity.class);
                    bundle.putInt("tag",0);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    loanProductsActivity_callBack.closeActivity();
                break;
                case 2:
                    intent.setClass(context, LoanApplicationActivity.class);
                    bundle.putInt("tag",1);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    loanProductsActivity_callBack.closeActivity();
                break;
        }
    }

    /**
     * 获取 以前的记录
     */
    @Override
    public void getCallBackData() {
        getLoanDetail_Request.request(context, new NetWorkCallBack<GetLoanDetail>() {
            @Override
            public void onSucceed(GetLoanDetail getLoanDetail,int dataMode) {
                GetLoanDetail detail = getLoanDetail;
                loanProductsActivity_callBack.setCallBackData(detail);
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }
}
