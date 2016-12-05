package com.xinxinxuedai.MVP.LoanApplicationActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.ui.ApplyForActivity;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:38 . 2016年12月02日
 * 描述:零用金借款申请P
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
            case 99:
                intent.setClass(context, ApplyForActivity.class);
                context.startActivity(intent);
            break;
            case 1:
                ArrayList<String> strings1 = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    strings1.add("1111测试"+i);
                }
                loanApplicationActivity_callBack.showDialog1(strings1);
            break;
            case 2:
                ArrayList<String> strings2 = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    strings2.add("2222测试"+i);
                }
                loanApplicationActivity_callBack.showDialog2(strings2);
                break;
            case 3:
                ArrayList<String> strings3 = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    strings3.add("3333测试"+i);
                }
                loanApplicationActivity_callBack.showDialog3(strings3);
            break;
            case 4:
                Hashtable<String,String> hashtable= new Hashtable<>();
                hashtable.put("1","1");
                //发送网络请求
                requestOperation(context, hashtable);
            break;
        }
    }

    /**
     * 调用网络请求 把 需要的参数 传过去
     *
     * @param context   上下文
     * @param hashtable 字段 内容等
     */
    @Override
    public void requestOperation(Context context, Hashtable hashtable) {
        //网络请求后
        loanApplicationActivity_callBack.getTextInfo4(hashtable.toString());

    }
}
