package com.xinxinxuedai.MVP.LoginActivity;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.UserLogin;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.UserLogin_Request;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 10:47 . 2016年12月04日
 * 描述:逻辑_登陆界面
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
//        switch ((int)view.getTag()){
//            case 1:
//
//            break;
//            case 2:
//
//            break;
//        }
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
            if (editText2.length()>=6){
                // UserRegist_Request
                //发送登陆的请求
                UtilsToast.showToast(context, "登陆中~");
                //去登陆
                call_Login(editText1, editText2);

            }else{
                UtilsToast.showToast(context, "密码长度小于6位");
                return;
            }

        }else{
            UtilsToast.showToast(context, "手机号码长度不正确");
            return;
        }



    }

    /**
     * 登陆请求
     * @param editText1 手机号码
     * @param editText2 密码
     */
    private void call_Login(EditText editText1, EditText editText2) {
        Hashtable<String, String> hashtable =
                UtilsHashtable.getHashtable();
        //手机号码
        hashtable.put("loan_mobile",editText1.getText().toString().trim());
        //密码
        hashtable.put("loan_pwd",editText2.getText().toString().trim());
        UserLogin_Request.request(context, hashtable, new NetWorkCallBack<UserLogin>() {
            @Override
            public void onSucceed(UserLogin login,int dataMode) {

                String message = login.message;
                UtilsToast.showToast(context, message);
                String loan_id = login.data.loan_id;
                Share.saveToken(context, loan_id);
                String loan_mobile = login.data.loan_mobile;
                Share.savePhoneNum(context, loan_mobile);

                loginActivity_c.closeActivity();
            }

            @Override
            public void onError(String jsonObject) {
                UtilsToast.showToast(context, jsonObject);
            }
        });
    }


}
