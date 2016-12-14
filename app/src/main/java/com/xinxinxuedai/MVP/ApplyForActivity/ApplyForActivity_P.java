package com.xinxinxuedai.MVP.ApplyForActivity;

import android.content.Context;
import android.content.Intent;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.request.GetInfo_Request;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.ui.BankCardInfoActivity;
import com.xinxinxuedai.ui.PersonalDetailsActivity;
import com.xinxinxuedai.ui.SchoolAddressActivity;
import com.xinxinxuedai.ui.UploadPicturesActivity;
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
                //完善信息
                intent.setClass(context, PersonalDetailsActivity.class);
                context.startActivity(intent);
            break;
            case 2:
                //学校信息
                intent.setClass(context, SchoolAddressActivity.class);
                context.startActivity(intent);
            break;
            case 3:
                //银行卡信息
                intent.setClass(context, BankCardInfoActivity.class);
                context.startActivity(intent);
            break;
            case 4:
                //上传照片啥的
                intent.setClass(context, UploadPicturesActivity.class);
                context.startActivity(intent);
            break;
        }
    }

    /**
     * 获取 info 信息
     */
    @Override
    public void getCallBackData() {
        GetInfo_Request.request(context, new NetWorkCallBack<GetInfo>() {
            @Override
            public void onSucceed(GetInfo getInfo) {
                applyForActivity_callback.setCallBackData(getInfo);
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }


}
