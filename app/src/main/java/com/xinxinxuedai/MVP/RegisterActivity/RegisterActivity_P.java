package com.xinxinxuedai.MVP.RegisterActivity;

import android.content.Context;
import android.view.View;

import com.xinxinxuedai.MVP.RegisterActivity.countTime.Register_countTime_P;
import com.xinxinxuedai.Utils.LogUtils;

/**
 * Created by 35876 于萌萌
 * 创建日期: 11:12 . 2016年12月01日
 * 描述:登陆界面的逻辑
 * <p>
 * <p>
 * 备注:
 */

public class RegisterActivity_P implements RegisterActivity_M {
    static RegisterActivity_P mRegisterActivity_p;
    Context context;
    private Register_countTime_P mRegister_countTime_p;

    public RegisterActivity_P(Context context){
        this.context = context;
        initcountTime_P();
    }
    public static RegisterActivity_P getLoginActivity_P(Context context){
        if (null== mRegisterActivity_p){
            return mRegisterActivity_p = new RegisterActivity_P(context);
        }else{

            return mRegisterActivity_p;
        }

    }


    private void initcountTime_P() {
        //mRegister_countTime_p = new Register_countTime_P(context);
        mRegister_countTime_p = Register_countTime_P.getLogin_countTime_P(context);

    }


    @Override
    public void onClick(View view) {
        switch ((int)view.getTag()){
            //按下去的是倒计时
            case 1:
                LogUtils.i("点击了注册/重置验证码倒计时");
                mRegister_countTime_p.startCountDown();
                //发送验证码的请求  SendBy_Request

            break;
            //按下去确定注册
            case 2:
                LogUtils.i("点击了注册");
            break;
            //按下去的是确认重置
            case 3:
                LogUtils.i("点击了重置");
            break;
        }
    }

    @Override
    public void startService() {

    }
}
