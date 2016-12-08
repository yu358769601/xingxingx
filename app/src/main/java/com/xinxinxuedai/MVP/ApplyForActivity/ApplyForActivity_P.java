package com.xinxinxuedai.MVP.ApplyForActivity;

import android.content.Context;
import android.content.Intent;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.ui.BankCardInfoActivity;
import com.xinxinxuedai.ui.OrganizingDataActivity;
import com.xinxinxuedai.ui.PersonalDetailsActivity;
import com.xinxinxuedai.ui.SchoolAddressActivity;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:50 . 2016年12月01日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class ApplyForActivity_P extends BaseMvp<ApplyForActivity_callback> implements ApplyForActivity_method{
    static ApplyForActivity_P mApplyForActivity_p;
    ApplyForActivity_callback applyForActivity_callback;

    static Context context;
    public ApplyForActivity_P(Context context){
        this.context = context;
    }
    public static ApplyForActivity_P getApplyForActivity_P(Context context){
        if (null == mApplyForActivity_p){
            return mApplyForActivity_p = new ApplyForActivity_P(context);
        }

        return mApplyForActivity_p;
    }
    @Override
    public void setCallBack(ApplyForActivity_callback applyForActivity_callback) {
            this.applyForActivity_callback = applyForActivity_callback;
    }

    @Override
    public void initClick(XueDaiButton xueDaiButton) {
        Intent intent = new Intent();
        switch (xueDaiButton.getType()){
            case 1:
                intent.setClass(context, PersonalDetailsActivity.class);
                context.startActivity(intent);
            break;
            case 2:
                intent.setClass(context, SchoolAddressActivity.class);
                context.startActivity(intent);
            break;
            case 3:
                intent.setClass(context, BankCardInfoActivity.class);
                context.startActivity(intent);
            break;
            case 4:
                intent.setClass(context, OrganizingDataActivity.class);
                context.startActivity(intent);
            break;
        }
    }


}
