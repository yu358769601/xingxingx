package com.xinxinxuedai.MVP.LoanApplicationActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.ui.ApplyForActivity;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:38 . 2016年12月02日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class LoanApplicationActivity_P  extends BaseMvp<LoanApplicationActivity_CallBack> implements  LoanApplicationActivity_method{
    LoanApplicationActivity_CallBack loanApplicationActivity_callBack;
    static LoanApplicationActivity_P sLoanApplicationActivity_p;
    Context context;
    public LoanApplicationActivity_P(Context context) {
        this.context = context;
    }

    @Override
    public void setCallBack(LoanApplicationActivity_CallBack loanApplicationActivity_callBack) {
        this.loanApplicationActivity_callBack = loanApplicationActivity_callBack;
    }

    public static LoanApplicationActivity_P getLoanApplicationActivity_P(Context context){
       if (null == sLoanApplicationActivity_p){
           return sLoanApplicationActivity_p = new LoanApplicationActivity_P(context);
       }else{
           return sLoanApplicationActivity_p;
       }
    }


    @Override
    public void clicks(View view) {
        Intent intent = new Intent();
        switch ((int)view.getTag()){
            case 1:
                intent.setClass(context, ApplyForActivity.class);
                context.startActivity(intent);
            break;
        }
    }
}
