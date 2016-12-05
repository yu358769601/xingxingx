package com.xinxinxuedai.MVP.LoginActivity;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsToast;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 10:47 . 2016年12月04日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class LoginActivity_P extends BaseMvp<LoginActivity_C> implements LoginActivity_M {
    static LoginActivity_P sLoginActivity_p;
    Context context;
    public LoginActivity_P(Context context) {
        this.context = context;
    }
    LoginActivity_C loginActivity_c;
    public static LoginActivity_P getLoginActivity_p(Context context){
        if (null==sLoginActivity_p){
            return sLoginActivity_p = new LoginActivity_P(context);
        }else{
            return sLoginActivity_p;
        }

    }


    @Override
    public void setCallBack(LoginActivity_C loginActivity_c) {
        this.loginActivity_c = loginActivity_c;
    }


    @Override
    public void Clicks(View view) {
        if (null!=loginActivity_c)
        loginActivity_c.callBackButton(view);
        switch ((int)view.getTag()){
            case 1:

            break;
            case 2:

            break;
        }
    }

    /**
     * 负责获取 所有的填写数据的方法
     *
     * @param editTextViews
     */
    @Override
    public void setEditTextViews(List<EditText> editTextViews) {
        EditText editText1 = new EditText(context);
        EditText editText2  = new EditText(context);
        if (null==editTextViews||editTextViews.size()==0){
            return;
        }
        for (int i = 0; i <editTextViews.size() ; i++) {
            EditText editText = editTextViews.get(i);
            String s = editText.getText().toString();
                //1判断他俩是否为空
                if (TextUtils.isEmpty(s)){
                    switch ((int) editText.getTag()){
                        case 1:
                            UtilsToast.showToast(context, "手机号为空");
                            return;
                        case 2:
                            UtilsToast.showToast(context, "密码为空");
                            return;
                    }
                }
            if ((int) editText.getTag()==1){
                editText1 =editTextViews.get(i);
            }

            if ((int) editText.getTag()==2){
                editText2 =editTextViews.get(i);
            }

        }

        //2在判断是否够长
        //手机号码是否够11位
        if (editText1.length()==11){
            //密码是否大于最小位数8
            if (editText2.length()>=8){
                // LoginRequest
                //发送登陆的请求
                UtilsToast.showToast(context, "登陆中~");
            }else{
                UtilsToast.showToast(context, "密码长度小于8位");
                return;
            }

        }else{
            UtilsToast.showToast(context, "手机号码长度不正确");
            return;
        }



    }


}