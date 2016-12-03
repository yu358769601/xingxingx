package com.xinxinxuedai.MVP.LoanProductsActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
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
        switch ((int)view.getTag()){

                case 1:
                    intent.setClass(context, LoanApplicationActivity.class);
                    context.startActivity(intent);
                break;
        }
    }
}