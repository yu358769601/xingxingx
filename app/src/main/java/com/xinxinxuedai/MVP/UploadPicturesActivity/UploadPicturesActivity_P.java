package com.xinxinxuedai.MVP.UploadPicturesActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.request.GetInfo_Request;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.ui.ChoiceActivity;

/**
 * Created by 35876 于萌萌
 * 创建日期: 10:01 . 2016年12月06日
 * 描述:上传图片_P
 * <p>
 * <p>
 * 备注:
 */

public class UploadPicturesActivity_P extends BaseMvp<UploadPicturesActivity_C> implements UploadPicturesActivity_M {
    Context context;

    public UploadPicturesActivity_P(Context context){
        this.context = context;
    }
    UploadPicturesActivity_C uploadPicturesActivity_c;
    @Override
    public void setCallBack(UploadPicturesActivity_C uploadPicturesActivity_c) {
        this.uploadPicturesActivity_c = uploadPicturesActivity_c;
    }


    /**
     * 照片按钮
     *
     * @param view
     */
    @Override
    public void xuedai_buttonClick(View view) {
        Intent intent = new Intent();
        intent.setClass(context, ChoiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        int tag = (int) view.getTag();
        switch (tag){
            case 1:
          //  UtilsToast.showToast(AppContext.getApplication(), "点了上传身份证正面");
                bundle.putInt("classTag",1);
                intent.putExtras(bundle);
                context.startActivity(intent);
            break;
            case 2:
          //  UtilsToast.showToast(AppContext.getApplication(), "点了上传身份证背面");
                bundle.putInt("classTag",2);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
            case 3:
         //   UtilsToast.showToast(AppContext.getApplication(), "点了手持身份证");
                bundle.putInt("classTag",3);
                intent.putExtras(bundle);
                context.startActivity(intent);
                break;
        }
    }

    /**
     * 获取所有信息 主要是要大区
     */
    @Override
    public void getCallBackData() {
        GetInfo_Request.request(context, new NetWorkCallBack<GetInfo>() {
            @Override
            public void onSucceed(GetInfo getInfo,int dataMode) {
                if (!TextUtils.isEmpty(getInfo.loan_area))
                    Share.save(context,"loan_area",getInfo.loan_area );
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }
}
