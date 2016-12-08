package com.xinxinxuedai.MVP.ReimbursementActivity;

import android.content.Context;
import android.content.Intent;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.ui.TopUpActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:26 . 2016年12月07日
 * 描述:我要还款_P
 * <p>
 * <p>
 * 备注:
 */

public class ReimbursementActivity_P extends BaseMvp<ReimbursementActivity_C> implements ReimbursementActivity_M{
    static ReimbursementActivity_P mReimbursementActivity_p;
    Context context;
    public ReimbursementActivity_P(Context context) {
        this.context = context;
    }

    public static ReimbursementActivity_P getReimbursementActivity_p(Context context){
        if (mReimbursementActivity_p==null){
            return mReimbursementActivity_p = new ReimbursementActivity_P(context);
        }

        return mReimbursementActivity_p;
    }

    ReimbursementActivity_C reimbursementActivity_c;
    @Override
    public void setCallBack(ReimbursementActivity_C reimbursementActivity_c) {
        this.reimbursementActivity_c = reimbursementActivity_c;
    }


    /**
     * 点击了支付
     */
    @Override
    public void topUp() {
        Intent intent = new Intent(context, TopUpActivity. class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
